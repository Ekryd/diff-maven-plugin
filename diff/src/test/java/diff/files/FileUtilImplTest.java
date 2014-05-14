package diff.files;

import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.io.File;
import java.util.Collection;

import static matcher.FileSlashMatcher.fileNameEndsWith;
import static net.time4tea.rsync.matcher.FileMatchers.withAbsolutePath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author bjorn
 * @since 2014-04-25
 */
public class FileUtilImplTest {
    @Test
    public void getFilesShouldReturnAllFilesRecursively() throws Exception {
        FileUtilImpl fileUtil = new FileUtilImpl();
        Collection<File> files = fileUtil.getFiles("src/test/resources/new/recursive", CanReadFileFilter.CAN_READ);

        Matcher[] expectedFiles = {
                withAbsolutePath(fileNameEndsWith("src/test/resources/new/recursive/inception.txt")),
                withAbsolutePath(fileNameEndsWith("src/test/resources/new/recursive/recursive/inception.txt")),
        };
        assertThat(files, containsInAnyOrder(expectedFiles));
    }
    
    @Test
    public void ignoredFoldersCaseInsensitiveShouldNotShow() throws Exception {
        PluginParameters parameters = new PluginParametersBuilder()
                .setExcludeRelativeFolders("new/ignore2", "old/IGNORE")
                .createPluginParameters();
        FileUtilImpl fileUtil = new FileUtilImpl();

        FolderFilter folderFilter = new FolderFilter(parameters, "src/test/resources");
        Collection<File> files = fileUtil.getFiles("src/test/resources", folderFilter);

        Matcher<Iterable<? super File>> filesMatch1 = hasItem(withAbsolutePath(
                fileNameEndsWith("src/test/resources/new/ignore2/ignore2.txt")));
        assertThat(files, not(filesMatch1));

        Matcher<Iterable<? super File>> filesMatch2 = hasItem(withAbsolutePath(
                fileNameEndsWith("src/test/resources/old/ignore/ignored.txt")));
        assertThat(files, not(filesMatch2));
    }

    @Test
    public void ignoredFoldersCaseSensitiveShouldShow() throws Exception {
        PluginParameters parameters = new PluginParametersBuilder()
                .setExcludeRelativeFolders("new/ignore2", "old/IGNORE")
                .setLetterHandling("CASE_SENSITIVE")
                .createPluginParameters();
        FileUtilImpl fileUtil = new FileUtilImpl();

        FolderFilter folderFilter = new FolderFilter(parameters, "src/test/resources");
        Collection<File> files = fileUtil.getFiles("src/test/resources", folderFilter);

        Matcher<Iterable<? super File>> filesMatch1 = hasItem(withAbsolutePath(
                fileNameEndsWith("src/test/resources/new/ignore2/ignore2.txt")));
        assertThat(files, not(filesMatch1));

        Matcher<Iterable<? super File>> filesMatch2 = hasItem(withAbsolutePath(
                fileNameEndsWith("src/test/resources/old/ignore/ignored.txt")));
        assertThat(files, filesMatch2);
    }
}
