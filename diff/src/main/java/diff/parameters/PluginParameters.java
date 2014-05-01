package diff.parameters;

public class PluginParameters {
    private final String oldFolder;
    private final String newFolder;
    private final Letters letters;
    private final String[] excludeRelativeFolders;

    public PluginParameters(String oldFolder, String newFolder, Letters letters, String[] excludeRelativeFolders) {
        this.oldFolder = oldFolder;
        this.newFolder = newFolder;
        this.letters = letters;
        this.excludeRelativeFolders = excludeRelativeFolders;
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

    public String[] getExcludeRelativeFolders() {
        return excludeRelativeFolders;
    }
}
