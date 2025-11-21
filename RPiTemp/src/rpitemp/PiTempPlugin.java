package rpitemp;

import PamModel.PamDependency;
import PamModel.PamPluginInterface;

public class PiTempPlugin implements PamPluginInterface {

	private String jarFile;
	
	@Override
	public String getDefaultName() {
		return PiTempControl.unitType;
	}

	@Override
	public String getHelpSetName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setJarFile(String jarFile) {
		this.jarFile = jarFile;
	}

	@Override
	public String getJarFile() {
		return jarFile;
	}

	@Override
	public String getDeveloperName() {
		return "Doug Gillespie";
	}

	@Override
	public String getContactEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getPamVerDevelopedOn() {
		return "2.2.18";
	}

	@Override
	public String getPamVerTestedOn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAboutText() {
		return "2.2.18";
	}

	@Override
	public String getClassName() {
		return PiTempControl.class.getName();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return PiTempControl.unitType;
	}

	@Override
	public String getMenuGroup() {
		return "Utilities";
	}

	@Override
	public String getToolTip() {
		return "Display Pi temperature in side panel";
	}

	@Override
	public PamDependency getDependency() {
		return null;
	}

	@Override
	public int getMinNumber() {
		return 0;
	}

	@Override
	public int getMaxNumber() {
		return 1;
	}

	@Override
	public int getNInstances() {
		return 0;
	}

	@Override
	public boolean isItHidden() {
		return false;
	}

	@Override
	public int allowedModes() {
		return 0;
	}

}
