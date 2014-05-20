package diff.parameters;

import java.io.File;
import java.util.Arrays;

public class PluginParameters {
    private final String oldFolder;
    private final String newFolder;
    private final CaseSensitivity caseSensitivity;
    private final String[] excludeRelativeFolders;
    private final File filesToRemoveOutputFile;

    public PluginParameters(String oldFolder, String newFolder, CaseSensitivity caseSensitivity, String[] excludeRelativeFolders, File filesToRemoveOutputFile) {
        this.oldFolder = oldFolder;
        this.newFolder = newFolder;
        this.caseSensitivity = caseSensitivity;
        this.excludeRelativeFolders = excludeRelativeFolders;
        this.filesToRemoveOutputFile = filesToRemoveOutputFile;
    }

    public String getOldFolder() {
        return oldFolder;
    }

    public String getNewFolder() {
        return newFolder;
    }

    public CaseSensitivity getCaseSensitivity() {
        return caseSensitivity;
    }

    public String[] getExcludeRelativeFolders() {
        return excludeRelativeFolders;
    }

    @Override
    public String toString() {
        return "PluginParameters{" +
                "oldFolder='" + oldFolder + '\'' +
                ", newFolder='" + newFolder + '\'' +
                ", caseSensitivity=" + caseSensitivity +
                ", excludeRelativeFolders=" + Arrays.toString(excludeRelativeFolders) +
                ", filesToRemoveOutputFile=" + filesToRemoveOutputFile +
                '}';
    }
}
