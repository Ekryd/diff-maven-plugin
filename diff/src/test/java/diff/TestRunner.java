package diff;

import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class TestRunner {
    @Ignore
    @Test
    public void compareTwoRealFolders() {
        // The file A.txt is not present in new folder, so it should be part of files to remove
        PluginParameters parameters = new PluginParametersBuilder()
                .setFolders("C:\\Bankdata\\Versions\\3.3.10\\PS31_BOOT\\APPS", "C:\\Bankdata\\Versions\\3.4.50\\PS31_BOOT\\APPS")
                .setExcludeRelativeFolders("SB", "ATM")
                .createPluginParameters();

        FolderParser folderParser = new FolderParser(null);
        folderParser.setup(parameters);
        folderParser.diff();

        List<String> filesToRemove = folderParser.getFilesToRemove();
        for (String fileToRemove : filesToRemove) {
            System.out.println(fileToRemove);
        }
    }

}
