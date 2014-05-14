package diff.files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.util.Collection;

/**
 * @author bjorn
 * @since 2014-04-25
 */
public class FileUtilImpl implements FileUtil {
    private static final IOFileFilter ALL_FILES = CanReadFileFilter.CAN_READ;

    public Collection<File> getFiles(String folderName, IOFileFilter folderFilter) {
        try {
        return FileUtils.listFiles(new File(folderName), ALL_FILES, folderFilter);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not list files in '" + folderName + "'", e);
        }
    }

    public String getAbsoluteFileName(String relativeFolderName) {
        return new File(relativeFolderName).getAbsolutePath();
    }
}
