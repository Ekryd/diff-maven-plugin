package diff;

import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.junit.Test;

import static matcher.FileSlashMatcher.fileNameEndsWith;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;


@SuppressWarnings("unchecked")
public class TestFilesChanged {

    @Test
    public void filesThatHaveDifferentFileSizeShouldBeChanged() {
        // The file A.txt is not present in new folder, so it should be part of files to remove
        PluginParameters parameters = new PluginParametersBuilder()
                .setFolders("src/test/resources/old", "src/test/resources/new")
                .setExcludeRelativeFolders("IGNORE", "IGNORE2", "LETTER", "recursive")
                .createPluginParameters();

        FolderParser folderParser = new FolderParser(null);
        folderParser.setup(parameters);
        folderParser.diff();

        assertThat(folderParser.getFilesChanged(), contains(
                fileNameEndsWith("size/SizeDiffer.txt")));
    }

}
