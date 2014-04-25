package diff;

import diff.files.FileUtil;
import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.junit.Test;
import refutils.ReflectionHelper;

import java.io.File;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
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
        helper.setField("oldFiles", Arrays.asList("A.txt", "B.txt"));
        helper.setField("newFiles", Arrays.asList("B.txt", "C.txt"));
        
        assertThat(folderParser.getFilesToRemove(), containsInAnyOrder("A.txt"));
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
                .setOldFolder("src/test/resources/old")
                .setNewFolder("src/test/resources/new")
                .createPluginParameters();
		
        FolderParser folderParser = new FolderParser();
        folderParser.setup(parameters);
        new ReflectionHelper(folderParser).setField(fileUtil);
        
        folderParser.diff();

        assertThat(folderParser.getFilesToRemove(), containsInAnyOrder("folder/A.txt"));
    }
}
