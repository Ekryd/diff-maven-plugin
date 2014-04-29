package diff;

import diff.files.FileUtil;
import diff.fileset.FileSet;
import diff.parameters.FileParameters;
import diff.parameters.Letters;
import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.junit.Test;
import refutils.ReflectionHelper;

import java.io.File;
import java.util.Arrays;

import static matcher.FileSlashMatcher.fileNameEndsWith;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author bjorn
 * @since 2014-04-25
 */
public class FolderParserTest {
    @Test
    public void removedFilesShouldBeOldFilesMinusNewFiles() throws Exception {
        FolderParser folderParser = new FolderParser();
        ReflectionHelper helper = new ReflectionHelper(folderParser);
        helper.setField("oldFiles", new FileSet(new FileParameters(Letters.CASE_SENSITIVE, new File("folder").getAbsolutePath())).setFiles(Arrays.asList(
                new File("A.txt"), new File("B.txt"))));
        helper.setField("newFiles", new FileSet(new FileParameters(Letters.CASE_SENSITIVE, new File("folder").getAbsolutePath())).setFiles(Arrays.asList(
                new File("B.txt"), new File("C.txt"))));

        assertThat(folderParser.getFilesToRemove(), containsInAnyOrder(endsWith("A.txt")));
    }

    @Test
    public void filesShouldBeLoadedFromFileUtil() throws Exception {
        FileUtil fileUtil = mock(FileUtil.class);
        when(fileUtil.getFiles("src/test/resources/old"))
                .thenReturn(Arrays.asList(
                        new File("src/test/resources/old/folder/A.txt"),
                        new File("src/test/resources/old/folder/B.txt")));
        when(fileUtil.getFiles("src/test/resources/new"))
                .thenReturn(Arrays.asList(
                        new File("src/test/resources/new/folder/B.txt"),
                        new File("src/test/resources/new/folder/C.txt")));
        when(fileUtil.getAbsoluteFileName("src/test/resources/old"))
                .thenReturn(new File("src/test/resources/old").getAbsolutePath());
        when(fileUtil.getAbsoluteFileName("src/test/resources/new"))
                .thenReturn(new File("src/test/resources/new").getAbsolutePath());


        PluginParameters parameters = new PluginParametersBuilder()
                .setFolders("src/test/resources/old", "src/test/resources/new")
                .createPluginParameters();

        FolderParser folderParser = new FolderParser();
        folderParser.setup(parameters);
        new ReflectionHelper(folderParser).setField(fileUtil);

        folderParser.diff();

        assertThat(folderParser.getFilesToRemove(), containsInAnyOrder(fileNameEndsWith("folder/A.txt")));
    }
}
