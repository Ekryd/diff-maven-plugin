package diff.parameters;

import java.io.File;

public class PluginParametersBuilder {
    private String oldFolder;
    private String newFolder;
    private CaseSensitivity caseSensitivity = CaseSensitivity.CASE_INSENSITIVE;
    private String[] excludeRelativeFolders = new String[0];
    private File filesToRemoveOutputFile;

    public PluginParameters createPluginParameters() {
        return new PluginParameters(oldFolder, newFolder, caseSensitivity, excludeRelativeFolders, filesToRemoveOutputFile);
    }

    public PluginParametersBuilder setFolders(String oldFolder, String newFolder) {
        this.oldFolder = oldFolder;
        this.newFolder = newFolder;
        return this;
    }

    public PluginParametersBuilder setCaseSensitivity(String caseSensitivity) {
        this.caseSensitivity = CaseSensitivity.fromString(caseSensitivity);
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
