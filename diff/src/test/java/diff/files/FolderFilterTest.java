package diff.files;

import diff.logger.PluginLogger;
import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.junit.Test;
import refutils.ReflectionHelper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static matcher.FileSlashMatcher.fileNameEndsWith;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
public class FolderFilterTest {
    PluginLogger logger = mock(PluginLogger.class);

    @Test
    public void endingSlashesShouldBeRemovedOnIgnoreFolders() throws NoSuchFieldException, IllegalAccessException {
        PluginParameters parameters = new PluginParametersBuilder().setExcludeRelativeFolders(
                "folder1",
                "/folder2/",
                "\\folder3\\",
                "folder4\\/\\/").createPluginParameters();
        FolderFilter folderFilter = new FolderFilter(logger, parameters, "base");

        List<String> excludeRelativeFolders = (List<String>) new ReflectionHelper(folderFilter).getField("excludeAbsoluteFolders");
        assertThat(excludeRelativeFolders,
                contains(fileNameEndsWith("/base/folder1"),
                        fileNameEndsWith("/base/folder2"),
                        fileNameEndsWith("/base/folder3"),
                        fileNameEndsWith("/base/folder4"))
        );
    }

    @Test
    public void excludedFoldersShouldBeLoggedAtDebugLevel() throws Exception {
        Path temp = Files.createTempDirectory("folder");
        String baseFolder = temp.toFile().getParent();
        String folderName = temp.toFile().getName();
        when(logger.isDebug()).thenReturn(true);

        PluginParameters parameters = new PluginParametersBuilder()
                .setExcludeRelativeFolders(folderName)
                .createPluginParameters();
        FolderFilter folderFilter = new FolderFilter(logger, parameters, baseFolder);

        boolean accept = folderFilter.accept(temp.toFile());

        assertThat(accept, is(false));
        verify(logger).debug("Excluding folder " + temp.toString());
    }

}