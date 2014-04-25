package diff;

import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.junit.Test;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;


public class TestFilesToRemove {

    @Test
    public void filesToRemoveShouldContainMissingFilesInNewFolder() {
        // The file A.txt is not present in new folder, so it should be part of files to remove
        PluginParameters parameters = new PluginParametersBuilder()
                .setOldFolder("src/test/resources/old")
                .setNewFolder("src/test/resources/new")
                .createPluginParameters();
		
        FolderParser folderParser = new FolderParser();
        folderParser.setup(parameters);
        folderParser.diff();

        assertThat(folderParser.getFilesToRemove(), containsInAnyOrder("folder/A.txt", "folder/C.txt"));
    }

    @Test
    public void filesToRemoveShouldContainMissingFilesInNewFolder2() {
        // The file A.txt is not present in new folder, so it should be part of files to remove
        PluginParameters parameters = new PluginParametersBuilder()
                .setOldFolder("src/test/resources/new/")
                .setNewFolder("src/test/resources/old/")
                .createPluginParameters();
		
        FolderParser folderParser = new FolderParser();
        folderParser.setup(parameters);
        folderParser.diff();

        assertThat(folderParser.getFilesToRemove(), containsInAnyOrder("folder/D.txt"));
    }
}
