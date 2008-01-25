package flapjack.gui;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import flapjack.data.*;
import flapjack.gui.navpanel.*;
import flapjack.gui.visualization.*;

/**
 * Navigation panel that is responsible for control of the tree-control that
 * forms the left-hand side column of the main interface; selections of which
 * dictate what will be displayed in the main right-hand panel.
 */
class NavPanel extends JPanel
	implements TreeSelectionListener, PropertyChangeListener
{
	private JTree tree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode root;

	private JSplitPane splitPane;
	private EmptyPanel emptyPanel = new EmptyPanel();

	// We maintain just one GenotypePanel that is used to display any dataset
	// as it would require too much memory to assign one per dataset
	private GenotypePanel gPanel = new GenotypePanel();

	NavPanel()
	{
		root = new DefaultMutableTreeNode("root");
		treeModel = new DefaultTreeModel(root);

		tree = new JTree(treeModel);
		tree.addTreeSelectionListener(this);
		tree.setCellRenderer(new TreeRenderer());
		tree.setRootVisible(false);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.addPropertyChangeListener(this);
		splitPane.setDividerLocation(Prefs.guiNavSplitsLocation);
		splitPane.setLeftComponent(new JScrollPane(tree));
		splitPane.setRightComponent(emptyPanel);

		setLayout(new BorderLayout());
		add(splitPane);
	}

	GenotypePanel getGenotypePanel()
		{ return gPanel; }

	public void propertyChange(PropertyChangeEvent e)
	{
		if (e.getSource() == splitPane)
			Prefs.guiNavSplitsLocation = splitPane.getDividerLocation();
	}

	void addDataSetNode(DataSet dataSet)
	{
		// Create the nodes for the dataset's folder and its children
		DataSetNode dataSetNode = new DataSetNode(dataSet);
		treeModel.insertNodeInto(dataSetNode, root, root.getChildCount());

		// Add a child node for selecting the visualization panel
		BaseNode selectedNode = addVisualizationNode(dataSetNode);


		// Update the tree with the new node(s)
		tree.setSelectionPath(new TreePath(selectedNode.getPath()));
		tree.scrollPathToVisible(new TreePath(selectedNode.getPath()));
	}

	private BaseNode addVisualizationNode(DataSetNode dataSetNode)
	{
		DataSet dataSet = dataSetNode.getDataSet();

		VisualizationNode node = new VisualizationNode(dataSet, gPanel);
		dataSetNode.add(node);

		return node;
	}

	public void valueChanged(TreeSelectionEvent e)
	{
		BaseNode node = (BaseNode) tree.getLastSelectedPathComponent();

		int location = splitPane.getDividerLocation();

		if (node != null)
			splitPane.setRightComponent(node.getPanel());
		else
			splitPane.setRightComponent(emptyPanel);

		splitPane.setDividerLocation(location);
	}

	private static class EmptyPanel extends JPanel
	{
		EmptyPanel()
		{
			setBackground(Color.white);
			setLayout(new BorderLayout());

			add(new JLabel(RB.getString("gui.NavPanel.emptyPanel"), JLabel.CENTER));
		}
	}
}