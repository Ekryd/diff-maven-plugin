package diff.parameters;

/**
 * @author bjorn
 * @since 2014-04-29
 */
public class FileParameters {

    private final Letters letters;
   private final String scanBaseFolderPathName;

    public FileParameters(Letters letters, String scanBaseFolderPathName) {
        this.letters = letters;
        this.scanBaseFolderPathName = scanBaseFolderPathName;
    }

    public Letters getLetters() {
        return letters;
    }

    public String getScanBaseFolderPathName() {
        return scanBaseFolderPathName;
    }
}
