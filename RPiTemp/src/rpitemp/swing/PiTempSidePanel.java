package rpitemp.swing;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import PamView.PamSidePanel;
import rpitemp.PiTempControl;

public class PiTempSidePanel implements PamSidePanel {

	private JPanel mainPanel;
	private PiTempControl piTempControl;
	
	private JLabel temp;
	
	public PiTempSidePanel(PiTempControl piTempControl) {
		this.piTempControl =piTempControl;
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new TitledBorder(piTempControl.getUnitName()));
		mainPanel.add(temp = new JLabel());
//		temp.setEnabled(false);
	}
	
	@Override
	public JComponent getPanel() {
		return mainPanel;
	}

	@Override
	public void rename(String newName) {
		// TODO Auto-generated method stub

	}
	
	public void setTemp(String tempStr) {
		temp.setText(tempStr);
	}

}
