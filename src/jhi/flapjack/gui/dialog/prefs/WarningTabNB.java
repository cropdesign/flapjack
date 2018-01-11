// Copyright 2009-2018 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package jhi.flapjack.gui.dialog.prefs;

import java.awt.*;
import javax.swing.*;

import jhi.flapjack.gui.*;

import scri.commons.gui.*;

class WarningTabNB extends JPanel implements IPrefsTab
{
	public WarningTabNB()
    {
        initComponents();

        setBackground((Color)UIManager.get("fjDialogBG"));
        panel.setBackground((Color)UIManager.get("fjDialogBG"));

		panel.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.dialog.prefs.NBWarningPanel.panelTitle")));

		RB.setText(warnDuplicateMarkers, "gui.dialog.prefs.NBWarningPanel.warnDuplicateMarkers");
		RB.setText(warnEditMarkerMode, "gui.dialog.prefs.NBWarningPanel.warnEditMarkerMode");
		RB.setText(warnEditLineMode, "gui.dialog.prefs.NBWarningPanel.warnEditLineMode");
		RB.setText(warnEditCustomMap, "gui.dialog.prefs.NBWarningPanel.warnEditCustomMap");

		initSettings();
    }

    private void initSettings()
    {
    	warnDuplicateMarkers.setSelected(Prefs.warnDuplicateMarkers);
		warnEditMarkerMode.setSelected(Prefs.warnEditMarkerMode);
		warnEditLineMode.setSelected(Prefs.warnEditLineMode);
		warnEditCustomMap.setSelected(Prefs.warnEditCustomMap);
    }

	public void applySettings()
	{
		Prefs.warnDuplicateMarkers = warnDuplicateMarkers.isSelected();
		Prefs.warnEditMarkerMode = warnEditMarkerMode.isSelected();
		Prefs.warnEditLineMode = warnEditLineMode.isSelected();
		Prefs.warnEditCustomMap = warnEditCustomMap.isSelected();
	}

	public void setDefaults()
	{
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        warnDuplicateMarkers = new javax.swing.JCheckBox();
        warnEditMarkerMode = new javax.swing.JCheckBox();
        warnEditLineMode = new javax.swing.JCheckBox();
        warnEditCustomMap = new javax.swing.JCheckBox();

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Inform me:"));

        warnDuplicateMarkers.setText("When duplicate markers are found during data import");

        warnEditMarkerMode.setText("When switching to 'marker mode'");

        warnEditLineMode.setText("When switching to 'line mode'");

        warnEditCustomMap.setText("When creating custom Chromosomes panel views");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warnDuplicateMarkers)
                    .addComponent(warnEditMarkerMode)
                    .addComponent(warnEditLineMode)
                    .addComponent(warnEditCustomMap))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(warnDuplicateMarkers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warnEditMarkerMode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warnEditLineMode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warnEditCustomMap))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private javax.swing.JCheckBox warnDuplicateMarkers;
    private javax.swing.JCheckBox warnEditCustomMap;
    private javax.swing.JCheckBox warnEditLineMode;
    private javax.swing.JCheckBox warnEditMarkerMode;
    // End of variables declaration//GEN-END:variables
}