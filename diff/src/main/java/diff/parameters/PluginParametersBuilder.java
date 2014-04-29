package diff.parameters;

public class PluginParametersBuilder {


    private String oldFolder;
    private String newFolder;
    private Letters letters;

    public PluginParametersBuilder setFolders(String oldFolder, String newFolder) {
        this.oldFolder = oldFolder;
        this.newFolder = newFolder;
        return this;
    }

    public PluginParametersBuilder setLetterHandling(Letters letters) {
        this.letters = letters;
        return this;
    }

    public PluginParameters createPluginParameters() {
        return new PluginParameters(oldFolder, newFolder, letters);
    }

}
