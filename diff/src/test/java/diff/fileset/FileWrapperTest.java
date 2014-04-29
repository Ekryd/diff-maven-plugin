package diff.fileset;

import diff.parameters.FileParameters;
import org.junit.Test;

import java.io.File;

import static diff.parameters.Letters.CASE_INSENSITIVE;
import static diff.parameters.Letters.CASE_SENSITIVE;
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
        FileParameters fileParameters = new FileParameters(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileParameters, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileParameters, new File("A.txt"));
        
        assertThat(fileWrapper1, equalTo(fileWrapper2));
    }

    @Test
    public void twoDifferentFilesShouldNotBeEqual() {
        FileParameters fileParameters = new FileParameters(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileParameters, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileParameters, new File("B.txt"));
        
        assertThat(fileWrapper1, not(equalTo(fileWrapper2)));
    }

    @Test
    public void twoFilesEqualityShouldBeCaseInsensitive() {
        FileParameters fileParameters = new FileParameters(CASE_INSENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileParameters, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileParameters, new File("a.txt"));
        
        assertThat(fileWrapper1, equalTo(fileWrapper2));
    }
    
    @Test
    public void twoIdenticalFilesShouldBeEqual2() {
        FileParameters fileParameters = new FileParameters(CASE_SENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileParameters, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileParameters, new File("A.txt"));
        
        assertThat(fileWrapper1, equalTo(fileWrapper2));
    }

    @Test
    public void twoDifferentFilesShouldNotBeEqual2() {
        FileParameters fileParameters = new FileParameters(CASE_SENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileParameters, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileParameters, new File("B.txt"));
        
        assertThat(fileWrapper1, not(equalTo(fileWrapper2)));
    }

    @Test
    public void twoFilesEqualityShouldBeCaseSensitive() {
        FileParameters fileParameters = new FileParameters(CASE_SENSITIVE, new File("folder").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileParameters, new File("A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileParameters, new File("a.txt"));
        
        assertThat(fileWrapper1, not(equalTo(fileWrapper2)));
    }
    
    @Test
    public void differentBaseDirectoryShouldCompareFiles() {
        FileParameters fileParameters1 = new FileParameters(CASE_SENSITIVE, new File("old").getAbsolutePath());
        FileParameters fileParameters2 = new FileParameters(CASE_SENSITIVE, new File("new").getAbsolutePath());
        FileWrapper fileWrapper1 = new FileWrapper(fileParameters1, new File("old/A.txt"));
        FileWrapper fileWrapper2 = new FileWrapper(fileParameters2, new File("new/A.txt"));
        
        assertThat(fileWrapper1, equalTo(fileWrapper2));
    }
    
}
