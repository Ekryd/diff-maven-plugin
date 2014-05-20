package diff;

import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.junit.Test;

import static matcher.FileSlashMatcher.fileNameEndsWith;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;


@SuppressWarnings("unchecked")
public class TestFilesToRemove {

    @Test
    public void filesToRemoveShouldContainMissingFilesInNewFolder() {
        // The file A.txt is not present in new folder, so it should be part of files to remove
        PluginParameters parameters = new PluginParametersBuilder()
                .setFolders("src/test/resources/old", "src/test/resources/new")
                .setExcludeRelativeFolders("IGNORE", "IGNORE2")
                .createPluginParameters();

        FolderParser folderParser = new FolderParser(null);
        folderParser.setup(parameters);
        folderParser.diff();

        assertThat(folderParser.getFilesToRemove(), containsInAnyOrder(
                fileNameEndsWith("folder/A.txt"),
                fileNameEndsWith("folder/C.txt")));
    }

    @Test
    public void filesToRemoveShouldContainMissingFilesInNewFolder2() {
        // The file A.txt is not present in new folder, so it should be part of files to remove
        PluginParameters parameters = new PluginParametersBuilder()
                .setFolders("src/test/resources/new/", "src/test/resources/old/")
                .setExcludeRelativeFolders("ignore", "ignore2", "recursive")
                .createPluginParameters();

        FolderParser folderParser = new FolderParser(null);
        folderParser.setup(parameters);
        folderParser.diff();

        assertThat(folderParser.getFilesToRemove(), contains(
                fileNameEndsWith("folder/D.txt")));
    }

    @Test
    public void caseSensitivityShouldIgnoreLettersInFoldersAndFileNames() {
        // The file A.txt is not present in new folder, so it should be part of files to remove
        // folder is not ignored by "FOLDER"
        PluginParameters parameters = new PluginParametersBuilder()
                .setFolders("src/test/resources/old", "src/test/resources/new")
                .setCaseSensitivity("CASE_SENSITIVE")
                .setExcludeRelativeFolders("ignore", "ignore2", "FOLDER")
                .createPluginParameters();

        FolderParser folderParser = new FolderParser(null);
        folderParser.setup(parameters);
        folderParser.diff();

        assertThat(folderParser.getFilesToRemove(),
                containsInAnyOrder(
                        fileNameEndsWith("folder/A.txt"),
                        fileNameEndsWith("folder/C.txt"),
                        fileNameEndsWith("letter/a.txt")
                )
        );
    }

}
