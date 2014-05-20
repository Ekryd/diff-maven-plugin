package diff.fileset;

import org.junit.Test;

import java.io.File;

import static diff.parameters.CaseSensitivity.CASE_INSENSITIVE;
import static diff.parameters.CaseSensitivity.CASE_SENSITIVE;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author bjorn
 * @since 2014-04-29
 */
public class FileWrapperTest {
    @Test
    public void twoIdenticalFilesShouldBeEqual() {
        FileWrapperBehaviour fileWrapperBehaviour = new FileWrapperBehaviour(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileWrapperBehaviour, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileWrapperBehaviour, new File("A.txt"));

        fileWrapperBehaviour.setFileNameEqualizer();
        
        assertThat(fileWrapper1, equalTo(fileWrapper2));
    }

    @Test
    public void twoDifferentFilesShouldNotBeEqual() {
        FileWrapperBehaviour fileWrapperBehaviour = new FileWrapperBehaviour(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileWrapperBehaviour, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileWrapperBehaviour, new File("B.txt"));

        fileWrapperBehaviour.setFileNameEqualizer();
        
        assertThat(fileWrapper1, not(equalTo(fileWrapper2)));
    }

    @Test
    public void twoFilesEqualityShouldBeCaseInsensitive() {
        FileWrapperBehaviour fileWrapperBehaviour = new FileWrapperBehaviour(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileWrapperBehaviour, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileWrapperBehaviour, new File("a.txt"));

        fileWrapperBehaviour.setFileNameEqualizer();
        
        assertThat(fileWrapper1, equalTo(fileWrapper2));
    }

    @Test
    public void twoIdenticalFilesShouldBeEqual2() {
        FileWrapperBehaviour fileWrapperBehaviour = new FileWrapperBehaviour(CASE_SENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileWrapperBehaviour, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileWrapperBehaviour, new File("A.txt"));

        fileWrapperBehaviour.setFileNameEqualizer();
        
        assertThat(fileWrapper1, equalTo(fileWrapper2));
    }

    @Test
    public void twoDifferentFilesShouldNotBeEqual2() {
        FileWrapperBehaviour fileWrapperBehaviour = new FileWrapperBehaviour(CASE_SENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileWrapperBehaviour, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileWrapperBehaviour, new File("B.txt"));

        fileWrapperBehaviour.setFileNameEqualizer();
        
        assertThat(fileWrapper1, not(equalTo(fileWrapper2)));
    }

    @Test
    public void twoFilesEqualityShouldBeCaseSensitive() {
        FileWrapperBehaviour fileWrapperBehaviour = new FileWrapperBehaviour(CASE_SENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileWrapperBehaviour, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileWrapperBehaviour, new File("a.txt"));

        fileWrapperBehaviour.setFileNameEqualizer();
        
        assertThat(fileWrapper1, not(equalTo(fileWrapper2)));
    }

    @Test
    public void differentBaseDirectoryShouldCompareFiles() {
        FileWrapperBehaviour fileWrapperBehaviour1 = new FileWrapperBehaviour(CASE_SENSITIVE, new File("old").getAbsolutePath());
        FileWrapperBehaviour fileWrapperBehaviour2 = new FileWrapperBehaviour(CASE_SENSITIVE, new File("new").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileWrapperBehaviour1, new File("old/A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileWrapperBehaviour2, new File("new/A.txt"));

        fileWrapperBehaviour1.setFileNameEqualizer();
        
        assertThat(fileWrapper1, equalTo(fileWrapper2));
    }
}
