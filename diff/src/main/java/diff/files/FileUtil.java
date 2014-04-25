package diff.files;

/**
 * @author bjorn
 * @since 2014-04-25
 */
public interface FileUtil {
    java.util.Collection<java.io.File> getFiles(String folderName);

    String getAbsoluteFileName(String relativeFolderName);
}
