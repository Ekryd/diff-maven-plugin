package diff;

import diff.logger.PluginLogger;
import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.junit.Test;

import static matcher.FileSlashMatcher.fileNameEndsWith;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;


@SuppressWarnings("unchecked")
public class TestFilesChanged {
    private PluginLogger logger = mock(PluginLogger.class);

    @Test
    public void filesThatHaveDifferentFileSizeShouldBeChanged() {
        // The file A.txt is not present in new folder, so it should be part of files to remove
        PluginParameters parameters = new PluginParametersBuilder()
                .setFolders("src/test/resources/old", "src/test/resources/new")
                .setExcludeRelativeFolders("IGNORE", "IGNORE2", "LETTER", "recursive")
                .createPluginParameters();

        FolderParser folderParser = new FolderParser(logger);
        folderParser.setup(parameters);
        folderParser.diff();

        assertThat(folderParser.getFilesChanged(), contains(
                fileNameEndsWith("size/SizeDiffer.txt")));
    }

}
