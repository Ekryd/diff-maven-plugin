package diff;

import diff.files.FileUtil;
import diff.fileset.FileSet;
import diff.logger.PluginLogger;
import diff.parameters.CaseSensitivity;
import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.junit.Test;
import refutils.ReflectionHelper;

import java.io.File;
import java.util.Arrays;

import static matcher.FileSlashMatcher.fileNameEndsWith;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author bjorn
 * @since 2014-04-25
 */
public class FolderParserTest {
    private final PluginLogger logger = mock(PluginLogger.class);

    @Test
    public void removedFilesShouldBeOldFilesMinusNewFiles() throws Exception {
        FolderParser folderParser = new FolderParser(logger);
        ReflectionHelper helper = new ReflectionHelper(folderParser);
        helper.setField("oldFiles", new FileSet(CaseSensitivity.CASE_SENSITIVE, new File("folder").getAbsolutePath()).setFiles(Arrays.asList(
                new File("A.txt"), new File("B.txt"))));
        helper.setField("newFiles", new FileSet(CaseSensitivity.CASE_SENSITIVE, new File("folder").getAbsolutePath()).setFiles(Arrays.asList(
                new File("B.txt"), new File("C.txt"))));

        assertThat(folderParser.getFilesToRemove(), contains(endsWith("A.txt")));
    }

    @Test
    public void filesShouldBeLoadedFromFileUtil() throws Exception {
        FileUtil fileUtil = mock(FileUtil.class);
        when(fileUtil.getFiles(eq("src/test/resources/old"), any(IOFileFilter.class)))
                .thenReturn(Arrays.asList(
                        new File("src/test/resources/old/folder/A.txt"),
                        new File("src/test/resources/old/folder/B.txt")));
        when(fileUtil.getFiles(eq("src/test/resources/new"), any(IOFileFilter.class)))
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

        FolderParser folderParser = new FolderParser(logger);
        folderParser.setup(parameters);
        new ReflectionHelper(folderParser).setField(fileUtil);

        folderParser.diff();

        assertThat(folderParser.getFilesToRemove(), contains(fileNameEndsWith("folder/A.txt")));
    }
    
    @Test
    public void pluginParametersShouldBeWrittenToDebugLog() {
        when(logger.isDebug()).thenReturn(true);
        
       PluginParameters parameters = new PluginParametersBuilder()
                .setFolders("src/test/resources/old", "src/test/resources/new")
                .createPluginParameters();
        
        FolderParser folderParser = new FolderParser(logger);
        folderParser.setup(parameters);
        
        verify(logger).debug("PluginParameters{oldFolder='src/test/resources/old', newFolder='src/test/resources/new', caseSensitivity=CASE_INSENSITIVE, excludeRelativeFolders=[], filesToRemoveOutputFile=null}");        
    }
}
