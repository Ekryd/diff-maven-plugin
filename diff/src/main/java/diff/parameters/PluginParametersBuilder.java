package diff.parameters;

import java.io.File;

public class PluginParametersBuilder {


    private String oldFolder;
    private String newFolder;
    private Letters letters = Letters.CASE_INSENSITIVE;
    private String[] excludeRelativeFolders = new String[0];
    private File filesToRemoveOutputFile;

    public PluginParameters createPluginParameters() {
        return new PluginParameters(oldFolder, newFolder, letters, excludeRelativeFolders, filesToRemoveOutputFile);
    }

    public PluginParametersBuilder setFolders(String oldFolder, String newFolder) {
        this.oldFolder = oldFolder;
        this.newFolder = newFolder;
        return this;
    }

    public PluginParametersBuilder setLetterHandling(String letters) {
        this.letters = Letters.fromString(letters);
        return this;
    }

    public PluginParametersBuilder setExcludeRelativeFolders(String... excludeRelativeFolders) {
        this.excludeRelativeFolders = excludeRelativeFolders;
        return this;
    }

    public PluginParametersBuilder setFilesToRemoveOutputFile(File filesToRemoveOutputFile) {
        this.filesToRemoveOutputFile = filesToRemoveOutputFile;
        return this;
    }
}
