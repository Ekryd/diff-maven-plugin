package diff.files;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.io.File;
import java.util.Collection;

import static net.time4tea.rsync.matcher.FileMatchers.withAbsolutePath;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;

/**
 * @author bjorn
 * @since 2014-04-25
 */
public class FileUtilImplTest {
    @Test
    public void getFilesShouldReturnAllFilesRecursively() throws Exception {
        FileUtilImpl fileUtil = new FileUtilImpl();
        Collection<File> files = fileUtil.getFiles("src/test/resources");

        Matcher[] expectedFiles = {
                withAbsolutePath(endsWith("src/test/resources/old/folder/A.txt")), 
                withAbsolutePath(endsWith("src/test/resources/old/folder/B.txt")),
                withAbsolutePath(endsWith("src/test/resources/old/folder/C.txt")),
                withAbsolutePath(endsWith("src/test/resources/new/folder/D.txt")),
                withAbsolutePath(endsWith("src/test/resources/new/folder/B.txt"))
        };
        assertThat(files, containsInAnyOrder(expectedFiles));                
    }
}
