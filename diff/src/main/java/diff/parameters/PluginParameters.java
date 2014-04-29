package diff.parameters;

public class PluginParameters {
	private final String oldFolder;
	private final String newFolder;
	private final Letters letters;
	
	public PluginParameters(String oldFolder, String newFolder, Letters letters) {
		this.oldFolder = oldFolder;
		this.newFolder = newFolder;
        this.letters = letters;
	}

	public String getOldFolder() {
		return oldFolder;
	}

	public String getNewFolder() {
		return newFolder;
	}

    public Letters getLetters() {
        return letters;
    }
}
