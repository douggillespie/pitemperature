package rpitemp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.Timer;

import PamController.PamControlledUnit;
import PamView.PamSidePanel;
import rpitemp.swing.PiTempSidePanel;

public class PiTempControl extends PamControlledUnit {

	public static final String unitType = "Pi Temperature";
	
	private PiTempSidePanel sidePanel;
	
	private Timer timer;
	
	public PiTempControl(String unitName) {
		super(unitType, unitName);
		timer = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getTemperature();
			}
		});
		timer.start();
	}

	protected void getTemperature() {
		String cmd = "vcgencmd measure_temp";
		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add(cmd);
		ProcessBuilder pb = new ProcessBuilder();
		Process process = null;
		InputStream inputStream;
		BufferedReader reader = null;
		String answer = null;
		String ud = System.getProperty("user.home");
		try {
			pb.command("sh", "-c", cmd);
			process = pb.start();
			inputStream = process.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			answer = reader.readLine();
		} catch (IOException e) {
//			System.out.println(e.getMessage());
			sidePanel.setTemp("Read error");
			return;
		}
		synchronized (this) {
			if (sidePanel != null) {
				sidePanel.setTemp(answer);
			}
		}
		
	}

	@Override
	public PamSidePanel getSidePanel() {
		synchronized (this) {
		if (sidePanel == null) {
			sidePanel = new PiTempSidePanel(this);
		}
		}
		return sidePanel;
	}

}
