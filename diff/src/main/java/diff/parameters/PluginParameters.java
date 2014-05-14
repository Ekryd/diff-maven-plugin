package diff.parameters;

import java.io.File;
import java.util.Arrays;

public class PluginParameters {
    private final String oldFolder;
    private final String newFolder;
    private final Letters letters;
    private final String[] excludeRelativeFolders;
    private final File filesToRemoveOutputFile;

    public PluginParameters(String oldFolder, String newFolder, Letters letters, String[] excludeRelativeFolders, File filesToRemoveOutputFile) {
        this.oldFolder = oldFolder;
        this.newFolder = newFolder;
        this.letters = letters;
        this.excludeRelativeFolders = excludeRelativeFolders;
        this.filesToRemoveOutputFile = filesToRemoveOutputFile;
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

    @Override
    public String toString() {
        return "PluginParameters{" +
                "oldFolder='" + oldFolder + '\'' +
                ", newFolder='" + newFolder + '\'' +
                ", letters=" + letters +
                ", excludeRelativeFolders=" + Arrays.toString(excludeRelativeFolders) +
                ", filesToRemoveOutputFile=" + filesToRemoveOutputFile +
                '}';
    }
}
