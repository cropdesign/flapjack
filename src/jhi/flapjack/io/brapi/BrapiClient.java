// Copyright 2009-2016 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package jhi.flapjack.io.brapi;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.*;
import java.util.zip.*;
import javax.xml.bind.*;

import jhi.flapjack.gui.*;

import jhi.brapi.resource.*;

import retrofit2.*;
import retrofit2.converter.jackson.*;

public class BrapiClient
{
	private BrapiService service;

	// The resource selected by the user for use
	private XmlResource resource;

	private String username;
	private String password;

	private String mapID;
	private String studyID;
	private String methodID;

	public void initService()
		throws Exception
	{
		String baseURL = resource.getUrl();
		baseURL = baseURL.endsWith("/") ? baseURL : baseURL + "/";

		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(baseURL)
			.addConverterFactory(JacksonConverterFactory.create())
			.build();

		service = retrofit.create(BrapiService.class);
	}

	private String enc(String str)
	{
		try { return URLEncoder.encode(str, "UTF-8"); }
		catch (UnsupportedEncodingException e) { return str; }
	}

	public void doAuthentication()
		throws Exception
	{
		if (username == null && password == null)
			return;

		BrapiSessionToken token = service.getAuthToken("password", enc(username), enc(password), "flapjack")
			.execute()
			.body();

//		String params = "grant_type=password&username=" + enc(username)
//			+ "&password=" + enc(password) + "&client_id=flapjack";
//		Form form = new Form(params);
//
//		BrapiSessionToken token = cr.post(form.getWebRepresentation(), BrapiSessionToken.class);
//
//		// Add the token information to all further calls
//		ChallengeResponse challenge = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
//		challenge.setRawValue(token.getSessionToken());
//		cr.setChallengeResponse(challenge);
	}

	// Returns a list of available maps
	public List<BrapiGenomeMap> getMaps()
		throws Exception
	{
		List<BrapiGenomeMap> list = new ArrayList<>();
		Pager pager = new Pager();

		while (pager.isPaging())
		{
			BasicResource<DataResult<BrapiGenomeMap>> br = service.getMaps(pager.getPageSize(), pager.getPage())
				.execute()
				.body();

			list.addAll(br.getResult().getData());

			pager.paginate(br.getMetadata());
		}

		return list;
	}

	// Returns the details (markers, chromosomes, positions) for a given map
	public List<BrapiMarkerPosition> getMapMarkerData()
		throws Exception
	{
		List<BrapiMarkerPosition> list = new ArrayList<>();
		Pager pager = new Pager();

		while (pager.isPaging())
		{
			BasicResource<DataResult<BrapiMarkerPosition>> br = service.getMapMarkerData(enc(mapID), pager.getPageSize(), pager.getPage())
				.execute()
				.body();

			list.addAll(br.getResult().getData());

			pager.paginate(br.getMetadata());
		}

		return list;
	}

	// Returns a list of available studies
	public List<BrapiStudies> getStudies()
		throws Exception
	{
		List<BrapiStudies> list = new ArrayList<>();
		Pager pager = new Pager();

		while (pager.isPaging())
		{
			BasicResource<DataResult<BrapiStudies>> br = service.getStudies("genotype", pager.getPageSize(), pager.getPage())
				.execute()
				.body();

			list.addAll(br.getResult().getData());

			pager.paginate(br.getMetadata());
		}

		return list;
	}

	public List<BrapiMarkerProfile> getMarkerProfiles()
		throws Exception
	{
		List<BrapiMarkerProfile> list = new ArrayList<>();
		Pager pager = new Pager();

		while (pager.isPaging())
		{
			BasicResource<DataResult<BrapiMarkerProfile>> br = service.getMarkerProfiles(studyID, pager.getPageSize(), pager.getPage())
				.execute()
				.body();

			list.addAll(br.getResult().getData());

			pager.paginate(br.getMetadata());
		}

		return list;
	}


	public List<BrapiAlleleMatrix> getAlleleMatrix(List<BrapiMarkerProfile> markerprofiles)
		throws Exception
	{
		List<BrapiAlleleMatrix> list = new ArrayList<>();
		Pager pager = new Pager();

		List<String> ids = markerprofiles.stream().map(BrapiMarkerProfile::getMarkerProfileDbId).collect(Collectors.toList());

		while (pager.isPaging())
		{
			BasicResource<BrapiAlleleMatrix> br = service.getAlleleMatrix(ids, pager.getPageSize(), pager.getPage())
				.execute()
				.body();

			ArrayList<BrapiAlleleMatrix> temp = new ArrayList<>();
			temp.add(br.getResult());
			list.addAll(temp);

			pager.paginate(br.getMetadata());
		}

		return list;
	}

	public URI getAlleleMatrixTSV(List<BrapiMarkerProfile> markerprofiles)
		throws Exception
	{
		List<String> ids = markerprofiles.stream().map(BrapiMarkerProfile::getMarkerProfileDbId).collect(Collectors.toList());

		BasicResource<BrapiAlleleMatrix> br = service.getAlleleMatrix(ids, "tsv", null, null)
			.execute()
			.body();

		jhi.brapi.resource.Metadata md = br.getMetadata();
		List<Datafile> files = md.getDatafiles();

		return new URI(files.get(0).getUrl());
	}

	public XmlBrapiProvider getBrapiProviders()
		throws Exception, IOException
	{
		URL url = new URL("https://ics.hutton.ac.uk/resources/brapi/brapi.zip");

		File dir = new File(FlapjackUtils.getCacheDir(), "brapi");
		dir.mkdirs();

		// Download the zip file and extract its contents into a temp folder
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(url.openStream()));
		ZipEntry ze = zis.getNextEntry();

    	while (ze != null)
		{
			BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(new File(dir, ze.getName())));
			BufferedInputStream in = new BufferedInputStream(zis);

			byte[] b = new byte[4096];
			for (int n; (n = in.read(b)) != -1;)
				out.write(b, 0, n);

			out.close();
			ze = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();


		// Now read the contents of the XML file
		JAXBContext jaxbContext = JAXBContext.newInstance(XmlBrapiProvider.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		File xml = new File(dir, "brapi.xml");

		return (XmlBrapiProvider) jaxbUnmarshaller.unmarshal(xml);
	}


	public String getUsername()
	{ return username; }

	public void setUsername(String username)
	{ this.username = username; }

	public String getPassword()
	{ return password; }

	public void setPassword(String password)
	{ this.password = password; }

	public String getMethodID()
	{ return methodID; }

	public void setMethodID(String methodID)
	{ this.methodID = methodID;	}

	public XmlResource getResource()
	{ return resource; }

	public void setResource(XmlResource resource)
	{ this.resource = resource; }

	public String getMapID()
	{ return mapID; }

	public void setMapID(String mapIndex)
	{ this.mapID = mapIndex; }

	public String getStudyID()
	{ return studyID; }

	public void setStudyID(String studyID)
	{ this.studyID = studyID; }

//	private static void initCertificates(Client client, XmlResource resource)
//		throws Exception
//	{
//		if (resource.getCertificate() == null)
//			return;
//
//		// Download the "trusted" certificate needed for this resource
//		URLConnection yc = new URL(resource.getCertificate()).openConnection();
//
//		CertificateFactory cf = CertificateFactory.getInstance("X.509");
//		InputStream in = new BufferedInputStream(yc.getInputStream());
//		java.security.cert.Certificate cer;
//		try {
//			cer = cf.generateCertificate(in);
//		} finally { in.close();	}
//
//		// Create a KeyStore to hold the certificate
//		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//		keyStore.load(null, null);
//		keyStore.setCertificateEntry("cer", cer);
//
//		// Create a TrustManager that trusts the certificate in the KeyStore
//		String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//		TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//		tmf.init(keyStore);
//
//		// Create an SSLContext that uses the TrustManager
//		SSLContext sslContext = SSLContext.getInstance("TLS");
//		sslContext.init(null, tmf.getTrustManagers(), null);
//
//		// Then *finally*, apply the TrustManager info to Restlet
//		client.setContext(new Context());
//		Context context = client.getContext();
//
//		context.getAttributes().put("sslContextFactory", new SslContextFactory() {
//		    public void init(Series<Parameter> parameters) { }
//		   	public SSLContext createSslContext() throws Exception { return sslContext; }
//		});
//	}

	class Pager
	{
		private boolean isPaging = true;
		private String pageSize = "1000";
		private String page = "0";

		// Returns true if another 'page' of data should be requested
		private void paginate(jhi.brapi.resource.Metadata metadata)
		{
			Pagination p = metadata.getPagination();

			if (p.getTotalPages() == 0)
				isPaging = false;

			if (p.getCurrentPage() == p.getTotalPages()-1)
				isPaging = false;

			// If it's ok to request another page, update the URL (for the next call)
			// so that it does so
			pageSize = "" + p.getPageSize();
			page = "" + (p.getCurrentPage()+1);
		}

		public boolean isPaging()
		{ return isPaging; }

		public void setPaging(boolean paging)
		{ isPaging = paging; }

		public String getPageSize()
		{ return pageSize; }

		public void setPageSize(String pageSize)
		{ this.pageSize = pageSize; }

		public String getPage()
		{ return page; }

		public void setPage(String page)
		{ this.page = page; }
	}
}