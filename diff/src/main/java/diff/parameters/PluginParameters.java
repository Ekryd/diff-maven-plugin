package diff.parameters;

public class PluginParameters {
	private final String oldFolder;
	private final String newFolder;
	
	public PluginParameters(String oldFolder, String newFolder) {
		this.oldFolder = oldFolder;
		this.newFolder = newFolder;
	}

	public String getOldFolder() {
		return oldFolder;
	}

	public String getNewFolder() {
		return newFolder;
	}

}
