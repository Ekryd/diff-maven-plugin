package diff;

import junit.framework.TestCase;
import org.junit.Ignore;

/**
 * @author bjorn
 * @since 2014-05-03
 */
@Ignore
public class FilesToRemoveMojoTest extends TestCase {
    // This is what I would like to test with test harness
    /*
    mvn com.google.code.diff-maven-plugin:diff-maven-plugin:0.0.1-SNAPSHOT:filesToRemove 
    -Ddiff.oldFolder="/Users/bjorn/ownCloud/Workspace/diff-plugin/diff/src/test/resources/old" 
    -Ddiff.newFolder="/Users/bjorn/ownCloud/Workspace/diff-plugin/diff/src/test/resources/new" 
    -Ddiff.letters="CASE_SENSITIVE" 
    -Ddiff.excludeRelativeFolders="ignore,letter"
    
    Expected: 
    [INFO] folder/A.txt
    [INFO] folder/C.txt

     */
    
}
