package diff.files;

import org.apache.commons.io.filefilter.IOFileFilter;

/**
 * @author bjorn
 * @since 2014-04-25
 */
public interface FileUtil {
    java.util.Collection<java.io.File> getFiles(String folderName, IOFileFilter folderFilter);

    String getAbsoluteFileName(String relativeFolderName);
}
