package diff.fileset;

import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.junit.Test;
import refutils.ReflectionHelper;

import java.util.List;

import static matcher.FileSlashMatcher.fileNameEndsWith;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class FolderFilterTest {
    @Test
    public void endingSlashesShouldBeRemovedOnIgnoreFolders() throws NoSuchFieldException, IllegalAccessException {
        PluginParameters parameters = new PluginParametersBuilder().setExcludeRelativeFolders(
                "folder1", 
                "/folder2/", 
                "\\folder3\\", 
                "folder4\\/\\/").createPluginParameters();
        FolderFilter folderFilter = new FolderFilter(parameters, "base");

        List<String> excludeRelativeFolders = (List<String>) new ReflectionHelper(folderFilter).getField("excludeAbsoluteFolders");
        assertThat(excludeRelativeFolders, 
                contains(fileNameEndsWith("/base/folder1"), 
                        fileNameEndsWith("/base/folder2"), 
                        fileNameEndsWith("/base/folder3"), 
                        fileNameEndsWith("/base/folder4")));
    }

}