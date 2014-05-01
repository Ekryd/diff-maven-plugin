package diff.fileset;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static diff.parameters.Letters.CASE_INSENSITIVE;
import static diff.parameters.Letters.CASE_SENSITIVE;
import static matcher.FileSlashMatcher.fileNameEndsWith;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")
public class TestFileSet {
    @Test
    public void newFileSetShouldNotHaveAnyFiles() {
        FileSet fileSet = new FileSet(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        assertThat(fileSet.size(), is(0));
    }

    @Test
    public void filesShouldBeConvertedToFileSet() {
        Collection<File> files = createMockFileCollection(42);

        FileSet fileSet = new FileSet(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        fileSet.setFiles(files);
        assertThat(fileSet.size(), is(42));
    }

    @Test
    public void getAbsoluteFileNamesShouldReturnAbsoluteFileNames() {
        Collection<File> files = createMockFileCollection(3);

        FileSet fileSet = new FileSet(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        fileSet.setFiles(files);

        assertThat(fileSet.getAbsoluteFilePaths(), containsInAnyOrder(
                fileNameEndsWith("diff-plugin/diff/File0.txt"),
                fileNameEndsWith("diff-plugin/diff/File1.txt"),
                fileNameEndsWith("diff-plugin/diff/File2.txt")
        ));
    }

    @Test
    public void removeAllOneFileSetFromAnotherShouldReturnNewCollection() {
        Collection<File> oldFiles = Arrays.asList(new File("A.txt"), new File("B.txt"), new File("C.txt"));
        Collection<File> newFiles = Arrays.asList(new File("B.txt"), new File("C.txt"), new File("D.txt"));

        FileSet oldFileSet = new FileSet(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        oldFileSet.setFiles(oldFiles);
        FileSet newFileSet = new FileSet(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        newFileSet.setFiles(newFiles);

        FileSet filesLeft = oldFileSet.removeAll(newFileSet);

        assertThat(filesLeft.getAbsoluteFilePaths(), contains(
                fileNameEndsWith("diff-plugin/diff/A.txt")
        ));
    }

    @Test
    public void defaultRemoveAllShouldBeCaseInsensitive() {
        Collection<File> oldFiles = Arrays.asList(new File("A.txt"), new File("B.txt"), new File("C.txt"));
        Collection<File> newFiles = Arrays.asList(new File("b.txt"), new File("c.txt"), new File("d.txt"));

        FileSet oldFileSet = new FileSet(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        oldFileSet.setFiles(oldFiles);
        FileSet newFileSet = new FileSet(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        newFileSet.setFiles(newFiles);

        FileSet filesLeft = oldFileSet.removeAll(newFileSet);

        assertThat(filesLeft.getAbsoluteFilePaths(), contains(
                fileNameEndsWith("diff-plugin/diff/A.txt")
        ));
    }

    @Test
    public void removeAllWithCaseSensitivityShouldListAllOldFiles() {
        Collection<File> oldFiles = Arrays.asList(new File("A.txt"), new File("B.txt"), new File("C.txt"));
        Collection<File> newFiles = Arrays.asList(new File("b.txt"), new File("c.txt"), new File("d.txt"));

        FileSet oldFileSet = new FileSet(CASE_SENSITIVE, new File("folder").getAbsolutePath());
        oldFileSet.setFiles(oldFiles);
        FileSet newFileSet = new FileSet(CASE_SENSITIVE, new File("folder").getAbsolutePath());
        newFileSet.setFiles(newFiles);

        FileSet filesLeft = oldFileSet.removeAll(newFileSet);

        assertThat(filesLeft.getAbsoluteFilePaths(), containsInAnyOrder(
                fileNameEndsWith("diff-plugin/diff/A.txt"),
                fileNameEndsWith("diff-plugin/diff/B.txt"),
                fileNameEndsWith("diff-plugin/diff/C.txt")
        ));
    }

    private Collection<File> createMockFileCollection(int size) {
        Collection<File> files = new ArrayList<File>(size);
        for (int i = 0; i < size; i++) {
            files.add(new File("File" + i + ".txt"));
        }
        return files;
    }

}
