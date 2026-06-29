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

	private String userHome;
	
	private volatile String lastTempStr;
	
	public PiTempControl(String unitName) {
		super(unitType, unitName);

		userHome = System.getProperty("user.home");
		
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
//		ArrayList<String> cmds = new ArrayList<String>();
//		cmds.add(cmd);
		ProcessBuilder pb = new ProcessBuilder();
		Process process = null;
		InputStream inputStream;
		BufferedReader reader = null;
		String answer = null;
		try {
			pb.command("sh", "-c", cmd);
			process = pb.start();
			inputStream = process.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			answer = reader.readLine();
		} catch (IOException e) {
//			System.out.println(e.getMessage());
			tellTemperature("Read error");
			return;
		}
		tellTemperature(answer);
			
		
	}
	
	private void tellTemperature(String temStr) {
		lastTempStr = temStr;
		synchronized (this) {
			if (sidePanel != null) {
				sidePanel.setTemp(temStr);
			}
		}
	}

	@Override
	public String getModuleSummary(boolean clear, String format) {
		return lastTempStr;
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
