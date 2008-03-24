package flapjack.gui.dialog;

import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.*;

import flapjack.data.*;

class NBExportImagePanel extends JPanel implements ChangeListener, ActionListener
{
	private DecimalFormat d = new DecimalFormat("0.00");

	private int lines, markers;
	private float lineMarkerRatio;

	SpinnerNumberModel widthModel, heightModel;

    public NBExportImagePanel(GTView view)
    {
		initComponents();

		lines = view.getLineCount();
		markers = view.getMarkerCount();

		lineMarkerRatio = (float)lines / (float)markers;

		equalCheck.addActionListener(this);

		widthModel  = new SpinnerNumberModel(markers, 1, 5 * markers, 1);
		heightModel = new SpinnerNumberModel(lines, 1, 5 * lines, 1);

		widthSpin.setModel(widthModel);
		widthSpin.addChangeListener(this);
		heightSpin.setModel(heightModel);
		heightSpin.addChangeListener(this);

		// The slider is tied to the value of the width input field
		slider.setMinimum(1);
		slider.setMaximum(5 * view.getMarkerCount());
		slider.setValue(markers);
		slider.addChangeListener(this);

		setMemoryText();
		setCheckBoxStates();
    }

    public void stateChanged(ChangeEvent e)
    {
    	// Remove the listeners so setting "a" doesn't generate events on "b"
    	// which then generates on "a" (until we run out of memory)
    	widthSpin.removeChangeListener(this);
    	heightSpin.removeChangeListener(this);
    	slider.removeChangeListener(this);

		// When a spinner is changed, modify the other one by the correct ratio
		// and update the slider's value
    	if (e.getSource() instanceof JSpinner)
    	{
    		int w = widthModel.getNumber().intValue();
    		int h = heightModel.getNumber().intValue();

    		if (e.getSource() == widthSpin)
    			heightModel.setValue(Math.round(w * lineMarkerRatio));
    		else
    			widthModel.setValue(Math.round(h / lineMarkerRatio));

    		slider.setValue(w);
    	}

    	// When the slider is changed, modify the two spinners
    	else if (e.getSource() instanceof JSlider)
    	{
    		int w = slider.getValue();

    		widthModel.setValue(w);
    		heightModel.setValue(Math.round(w * lineMarkerRatio));
    	}

    	widthSpin.addChangeListener(this);
    	heightSpin.addChangeListener(this);
    	slider.addChangeListener(this);

    	setMemoryText();
    	setCheckBoxStates();
    }

    private void setMemoryText()
    {
    	long w = widthModel.getNumber().longValue();
    	long h = heightModel.getNumber().longValue();

    	long memory = w * h * 3;

    	if (memory < Math.pow(1024, 2))
    		memLabel2.setText((long)(memory/1024f) + " KB");

    	else if (memory < Math.pow(1024, 3))
    		memLabel2.setText(d.format(memory/1024f/1024f) + " MB");

    	else
    		memLabel2.setText(d.format(memory/1024f/1024f/1024f) + " GB");
    }

    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource() == equalCheck && equalCheck.isSelected())
    		widthModel.setValue(markers);
    }

	private void setCheckBoxStates()
	{
		long w = slider.getValue();
    	equalCheck.setSelected(w == markers);
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        selectLabel = new javax.swing.JLabel();
        rWindow = new javax.swing.JRadioButton();
        rView = new javax.swing.JRadioButton();
        rOverview = new javax.swing.JRadioButton();
        widthLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        memLabel1 = new javax.swing.JLabel();
        memLabel2 = new javax.swing.JLabel();
        widthSpin = new javax.swing.JSpinner();
        heightSpin = new javax.swing.JSpinner();
        slider = new javax.swing.JSlider();
        equalCheck = new javax.swing.JCheckBox();
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();

        selectLabel.setText("Select a method for exporting:");

        buttonGroup1.add(rWindow);
        rWindow.setMnemonic('o');
        rWindow.setText("Export only what can currently be seen");
        rWindow.setDisplayedMnemonicIndex(7);

        buttonGroup1.add(rView);
        rView.setMnemonic('a');
        rView.setText("Export all of the current view");

        buttonGroup1.add(rOverview);
        rOverview.setMnemonic('s');
        rOverview.setText("Export a scaled-to-fit image of all of the data:");

        widthLabel.setDisplayedMnemonic('w');
        widthLabel.setText("Width (pixels):");

        heightLabel.setDisplayedMnemonic('h');
        heightLabel.setText("Height (pixels):");

        memLabel1.setText("Estimated memory required for exporting:");

        memLabel2.setText("<memory>");

        equalCheck.setMnemonic('p');
        equalCheck.setText("set pixel size equal to no. of markers by no. of lines");

        label1.setText("(creates a high-quality image showing exactly what you currently see)");

        label2.setText("(creates a high-quality image showing everything Flapjack is currently rendering)");

        label3.setText("(creates an overview image using the dimensions specified below)");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(label1))
                    .add(rOverview)
                    .add(layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(label2))
                    .add(rView)
                    .add(selectLabel)
                    .add(rWindow)
                    .add(layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(label3)
                            .add(layout.createSequentialGroup()
                                .add(widthLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(widthSpin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(heightLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(heightSpin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(slider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 119, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(equalCheck)))
                    .add(layout.createSequentialGroup()
                        .add(memLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(memLabel2)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(selectLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(rWindow)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(label1)
                .add(7, 7, 7)
                .add(rView)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(label2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(rOverview)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(label3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(widthLabel)
                    .add(widthSpin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(heightLabel)
                    .add(heightSpin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(slider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(equalCheck)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(memLabel1)
                    .add(memLabel2))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox equalCheck;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JSpinner heightSpin;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel memLabel1;
    private javax.swing.JLabel memLabel2;
    private javax.swing.JRadioButton rOverview;
    private javax.swing.JRadioButton rView;
    private javax.swing.JRadioButton rWindow;
    private javax.swing.JLabel selectLabel;
    private javax.swing.JSlider slider;
    private javax.swing.JLabel widthLabel;
    private javax.swing.JSpinner widthSpin;
    // End of variables declaration//GEN-END:variables
}