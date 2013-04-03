// Copyright 2009-2013 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package flapjack.gui.dialog;

import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

import flapjack.data.*;
import flapjack.gui.*;
import flapjack.gui.visualization.*;
import flapjack.gui.visualization.colors.*;

import scri.commons.gui.*;

class AlleleFrequencyPanelNB extends JPanel implements ChangeListener
{
	private GenotypePanel gPanel;
	private GTViewSet viewSet;

	AlleleFrequencyPanelNB(GenotypePanel gPanel)
	{
		this.gPanel = gPanel;
		this.viewSet = gPanel.getViewSet();

		setBackground((Color)UIManager.get("fjDialogBG"));
		initComponents();

		RB.setText(thresholdLabel, "gui.dialog.NBAlleleFrequencyPanel.thresholdLabel");
		RB.setText(hintLabel, "gui.dialog.NBAlleleFrequencyPanel.hintLabel");

		slider.addChangeListener(this);
		int value = (int) (viewSet.getAlleleFrequencyThreshold() * 200);
		slider.setValue(value);
	}

	public void stateChanged(ChangeEvent e)
	{
		int value = slider.getValue();
		float threshold = value / 200f;

		float percent = value / 2f;
		percentLabel.setText(percent + "%");

		viewSet.setAlleleFrequencyThreshold(threshold);
		viewSet.setColorScheme(ColorScheme.ALLELE_FREQUENCY);
		gPanel.refreshView();

		Actions.projectModified();
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        thresholdLabel = new javax.swing.JLabel();
        slider = new javax.swing.JSlider();
        percentLabel = new javax.swing.JLabel();
        hintLabel = new javax.swing.JLabel();

        thresholdLabel.setLabelFor(slider);
        thresholdLabel.setText("Low/high cutoff threshold:");

        slider.setMajorTickSpacing(50);
        slider.setMaximum(200);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);

        percentLabel.setText("50%");

        hintLabel.setText("To adjust this threshold later on, simply reapply the colour scheme");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(thresholdLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(percentLabel))
                    .addComponent(hintLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thresholdLabel)
                    .addComponent(percentLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hintLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hintLabel;
    private javax.swing.JLabel percentLabel;
    private javax.swing.JSlider slider;
    private javax.swing.JLabel thresholdLabel;
    // End of variables declaration//GEN-END:variables

}