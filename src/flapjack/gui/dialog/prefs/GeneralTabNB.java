// Copyright 2007-2011 Plant Bioinformatics Group, SCRI. All rights reserved.
// Use is subject to the accompanying licence terms.

package flapjack.gui.dialog.prefs;

import java.awt.*;
import javax.swing.*;

import flapjack.gui.*;

import scri.commons.gui.*;

class GeneralTabNB extends JPanel implements IPrefsTab
{
	private DefaultComboBoxModel displayModel;
	private DefaultComboBoxModel updateModel;

    public GeneralTabNB()
    {
        initComponents();

        setBackground((Color)UIManager.get("fjDialogBG"));
        generalPanel.setBackground((Color)UIManager.get("fjDialogBG"));
        projectPanel.setBackground((Color)UIManager.get("fjDialogBG"));

		// Interface settings
		generalPanel.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.dialog.prefs.NBGeneralPanel.generalPanelTitle")));
		RB.setText(displayLabel, "gui.dialog.prefs.NBGeneralPanel.displayLabel");
		RB.setText(displayHint, "gui.dialog.prefs.NBGeneralPanel.displayHint");

        displayModel = new DefaultComboBoxModel();
        displayModel.addElement(RB.getString("gui.dialog.prefs.NBGeneralPanel.auto"));
        displayModel.addElement(RB.getString("gui.dialog.prefs.NBGeneralPanel.en_GB"));
        displayModel.addElement(RB.getString("gui.dialog.prefs.NBGeneralPanel.en_US"));
        displayModel.addElement(RB.getString("gui.dialog.prefs.NBGeneralPanel.de"));
        displayCombo.setModel(displayModel);


        // Update settings
        RB.setText(updateLabel, "gui.dialog.prefs.NBGeneralPanel.updateLabel");

        updateModel = new DefaultComboBoxModel();
        updateModel.addElement(RB.getString("gui.dialog.prefs.NBGeneralPanel.updateNever"));
        updateModel.addElement(RB.getString("gui.dialog.prefs.NBGeneralPanel.updateStartup"));
        updateModel.addElement(RB.getString("gui.dialog.prefs.NBGeneralPanel.updateDaily"));
        updateModel.addElement(RB.getString("gui.dialog.prefs.NBGeneralPanel.updateWeekly"));
        updateModel.addElement(RB.getString("gui.dialog.prefs.NBGeneralPanel.updateMonthly"));
        updateCombo.setModel(updateModel);

		projectPanel.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.dialog.prefs.NBGeneralPanel.projectPanelTitle")));
		RB.setText(projectLabel, "gui.dialog.prefs.NBGeneralPanel.projectLabel");
		projectCombo.addItem(RB.getString("gui.dialog.prefs.NBGeneralPanel.projectXmlz"));
		projectCombo.addItem(RB.getString("gui.dialog.prefs.NBGeneralPanel.projectXml"));
		projectCombo.addItem(RB.getString("gui.dialog.prefs.NBGeneralPanel.projectBin"));

        initSettings();
    }

    private int getLocaleIndex()
	{
		if (Prefs.localeText.equals("en_GB"))
			return 1;
		else if (Prefs.localeText.equals("en_US"))
			return 2;
		else if (Prefs.localeText.equals("de"))
			return 3;
		else
			return 0;
	}

	private void initSettings()
    {
    	displayCombo.setSelectedIndex(getLocaleIndex());
    	updateCombo.setSelectedIndex(Prefs.guiUpdateSchedule);
		projectCombo.setSelectedIndex(Prefs.ioProjectFormat);
    }

	public void applySettings()
	{
		switch (displayCombo.getSelectedIndex())
		{
			case 1:  Prefs.localeText = "en_GB"; break;
			case 2:  Prefs.localeText = "en_US"; break;
			case 3:  Prefs.localeText = "de_DE"; break;
			default: Prefs.localeText = "auto";
		}

		Prefs.guiUpdateSchedule = updateCombo.getSelectedIndex();
		Prefs.ioProjectFormat = projectCombo.getSelectedIndex();
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

        generalPanel = new javax.swing.JPanel();
        displayCombo = new javax.swing.JComboBox();
        updateCombo = new javax.swing.JComboBox();
        displayLabel = new javax.swing.JLabel();
        updateLabel = new javax.swing.JLabel();
        displayHint = new javax.swing.JLabel();
        projectPanel = new javax.swing.JPanel();
        projectCombo = new javax.swing.JComboBox();
        projectLabel = new javax.swing.JLabel();

        generalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("General options:"));

        displayLabel.setLabelFor(displayCombo);
        displayLabel.setText("Interface display language:");

        updateLabel.setLabelFor(updateCombo);
        updateLabel.setText("Check for newer Flapjack versions:");

        displayHint.setText("(Restart Flapjack to apply)");

        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayLabel)
                    .addComponent(updateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateCombo, 0, 158, Short.MAX_VALUE)
                    .addComponent(displayHint)
                    .addComponent(displayCombo, 0, 158, Short.MAX_VALUE))
                .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayLabel)
                    .addComponent(displayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayHint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateLabel)
                    .addComponent(updateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        projectPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Project options:"));

        projectLabel.setText("Format for saving projects:");

        javax.swing.GroupLayout projectPanelLayout = new javax.swing.GroupLayout(projectPanel);
        projectPanel.setLayout(projectPanelLayout);
        projectPanelLayout.setHorizontalGroup(
            projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(projectLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(projectCombo, 0, 191, Short.MAX_VALUE)
                .addContainerGap())
        );
        projectPanelLayout.setVerticalGroup(
            projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, projectPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(projectLabel)
                    .addComponent(projectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(projectPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(generalPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(projectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox displayCombo;
    private javax.swing.JLabel displayHint;
    private javax.swing.JLabel displayLabel;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JComboBox projectCombo;
    private javax.swing.JLabel projectLabel;
    private javax.swing.JPanel projectPanel;
    private javax.swing.JComboBox updateCombo;
    private javax.swing.JLabel updateLabel;
    // End of variables declaration//GEN-END:variables
}