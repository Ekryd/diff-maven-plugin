package diff.parameters;

public class PluginParametersBuilder {


    private String oldFolder;
    private String newFolder;

    public PluginParametersBuilder setOldFolder(String oldFolder) {
        this.oldFolder = oldFolder;
        return this;
    }

    public PluginParametersBuilder setNewFolder(String newFolder) {
        this.newFolder = newFolder;
        return this;
    }

    public PluginParameters createPluginParameters() {
        return new PluginParameters(oldFolder, newFolder);
    }
}
