// Copyright 2009-2013 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package flapjack.gui.dialog;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import flapjack.gui.*;

import java.util.LinkedList;
import scri.commons.gui.*;
import scri.commons.gui.matisse.HistoryComboBox;

class ImportGenoTabNB extends javax.swing.JPanel implements ActionListener
{
	private JDialog parent;

	LinkedList<String> recentMapFiles = new LinkedList<String>();
	LinkedList<String> recentGenoFiles = new LinkedList<String>();

	ImportGenoTabNB(JDialog parent)
	{
		initComponents();

		this.parent = parent;

		setBackground((Color)UIManager.get("fjDialogBG"));
		filePanel.setBackground((Color)UIManager.get("fjDialogBG"));
		optionsPanel.setBackground((Color)UIManager.get("fjDialogBG"));

		mapButton.addActionListener(this);
		genoButton.addActionListener(this);
		optionsButton.addActionListener(this);

		mapComboBox.setHistory(Prefs.guiMapList);
		genoComboBox.setHistory(Prefs.guiGenoList);
		mapComboBox.setPrototypeDisplayValue("");
		genoComboBox.setPrototypeDisplayValue("");


		// Apply localized text
		RB.setText(tabLabel, "gui.dialog.NBDataImportPanel.tabLabel");
		filePanel.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.dialog.NBDataImportPanel.filePanel")));
		RB.setText(mapLabel, "gui.dialog.NBDataImportPanel.mapLabel");
		mapButton.setText(RB.getString("gui.text.browse"));
		RB.setText(genoLabel, "gui.dialog.NBDataImportPanel.genoLabel");
		genoButton.setText(RB.getString("gui.text.browse"));
		optionsPanel.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.dialog.NBDataImportPanel.optionsPanel")));
		RB.setText(optionsLabel, "gui.dialog.NBDataImportPanel.optionsLabel");
		RB.setText(optionsButton, "gui.dialog.NBDataImportPanel.optionsButton");

		mapComboBox.addActionListener(this);
		genoComboBox.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == mapButton)
			browse(mapComboBox, recentMapFiles);

		else if (e.getSource() == genoButton)
			browse(genoComboBox, recentGenoFiles);

		else if (e.getSource() == optionsButton)
			new ImportGenoAdvDialog(parent);
	}

	private void browse(HistoryComboBox combo, LinkedList<String> recentFiles)
	{
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(RB.getString("gui.dialog.NBDataImportPanel.fcTitle"));
		fc.setCurrentDirectory(new File(Prefs.guiCurrentDir));

		if (combo.getText().length() > 0)
			fc.setCurrentDirectory(new File(combo.getText()));

//		Filters.setFilters(fc, -1, FAS, PHY_S, PHY_I, ALN, MSF, NEX, NEX_B);

		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();
			Prefs.guiCurrentDir = fc.getCurrentDirectory().toString();

			combo.updateComboBox(file.toString());
		}
	}

	boolean isOK()
	{
		if (mapComboBox.getText().length() == 0 || genoComboBox.getText().length() == 0)
		{
			TaskDialog.warning(
				RB.getString("gui.dialog.NBDataImportPanel.warn1"),
				RB.getString("gui.text.ok"));

			return false;
		}

//		mapComboBox.updateComboBox(mapComboBox.getText());
//		genoComboBox.updateComboBox(genoComboBox.getText());
		Prefs.guiMapList = mapComboBox.getHistory();
		Prefs.guiGenoList = genoComboBox.getHistory();

		return true;
	}

	File getMapFile()
	{
		return new File(mapComboBox.getText());
	}

	File getGenotypeFile()
	{
		return new File(genoComboBox.getText());
	}



	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filePanel = new javax.swing.JPanel();
        mapLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        mapComboBox = new scri.commons.gui.matisse.HistoryComboBox();
        mapButton = new javax.swing.JButton();
        genoLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        genoComboBox = new scri.commons.gui.matisse.HistoryComboBox();
        genoButton = new javax.swing.JButton();
        tabLabel = new javax.swing.JLabel();
        optionsPanel = new javax.swing.JPanel();
        optionsButton = new javax.swing.JButton();
        optionsLabel = new javax.swing.JLabel();

        filePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data files to import:"));

        mapLabel.setLabelFor(mapComboBox);
        mapLabel.setText("Map file:");

        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(mapComboBox, java.awt.BorderLayout.CENTER);

        mapButton.setText("Browse...");

        genoLabel.setLabelFor(genoComboBox);
        genoLabel.setText("Genotype file:");

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(genoComboBox, java.awt.BorderLayout.CENTER);

        genoButton.setText("Browse...");

        javax.swing.GroupLayout filePanelLayout = new javax.swing.GroupLayout(filePanel);
        filePanel.setLayout(filePanelLayout);
        filePanelLayout.setHorizontalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genoLabel)
                    .addComponent(mapLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genoButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mapButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        filePanelLayout.setVerticalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(mapLabel)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mapButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(genoLabel)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genoButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabLabel.setText("Use this tab to import map and genotype data into a new or existing Flapjack project.");

        optionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Advanced options:"));

        optionsButton.setText("Advanced options...");

        optionsLabel.setText("Edit the advanced options to adjust how Flapjack will process the files being imported.");

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionsLabel)
                    .addComponent(optionsButton))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabLabel)
                .addGap(18, 18, 18)
                .addComponent(filePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel filePanel;
    private javax.swing.JButton genoButton;
    scri.commons.gui.matisse.HistoryComboBox genoComboBox;
    private javax.swing.JLabel genoLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton mapButton;
    scri.commons.gui.matisse.HistoryComboBox mapComboBox;
    private javax.swing.JLabel mapLabel;
    private javax.swing.JButton optionsButton;
    private javax.swing.JLabel optionsLabel;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JLabel tabLabel;
    // End of variables declaration//GEN-END:variables

}