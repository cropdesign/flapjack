// Copyright 2009-2013 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package flapjack.gui.dialog;

import java.awt.*;
import javax.swing.*;

import flapjack.data.*;
import flapjack.gui.*;

import scri.commons.gui.*;

class SelectLMPanelNB extends javax.swing.JPanel
{
	public SelectLMPanelNB(GTView view, boolean selectLines)
	{
		initComponents();

		setBackground((Color)UIManager.get("fjDialogBG"));

		if (selectLines)
		{
			RB.setText(label, "gui.dialog.NBSelectLMPanel.lineLabel");

			for (int i = 0; i < view.lineCount(); i++)
				combo.addItem(new IndexWrapper(view.getLineInfo(i).name(), i));

			if (view.mouseOverLine >= 0 && view.mouseOverLine < view.lineCount())
				combo.setSelectedIndex(view.mouseOverLine);
		}
		else
		{
			RB.setText(label, "gui.dialog.NBSelectLMPanel.markerLabel");

			boolean canPreSelect = false;
			int preSelectIndex = -1;
			if (view.mouseOverMarker >= 0 && view.mouseOverMarker < view.markerCount())
				canPreSelect = true;

			for (int i = 0; i < view.markerCount(); i++)
			{
				// Don't add dummy markers to the list
				if (view.getMarker(i).dummyMarker())
					continue;

				IndexWrapper w = new IndexWrapper(view.getMarker(i).getName(), i);
				combo.addItem(w);

				// If this marker is under the mouse, rememeber its index
				// position in the list for later selection
				if (canPreSelect && view.mouseOverMarker == i)
					preSelectIndex = combo.getItemCount()-1;
			}

			if (canPreSelect && preSelectIndex != -1)
				combo.setSelectedIndex(preSelectIndex);
		}
	}

	// A wrapper around a string. Also stores the index of the actual position
	// of this element (line or marker) back in the view. This is so dummy
	// markers can be skipped over - they aren't added to the combo box, but we
	// still want to know the index of the original marker once the user has
	// made a selection.
	static class IndexWrapper
	{
		String name;
		int index;

		IndexWrapper(String name, int index)
		{
			this.name = name;
			this.index = index;
		}

		public String toString()
			{ return name; }
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<IndexWrapper>();

        label.setLabelFor(combo);
        label.setText("Select comparison line:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo, 0, 255, Short.MAX_VALUE)
                    .addComponent(label))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JComboBox<IndexWrapper> combo;
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables
}