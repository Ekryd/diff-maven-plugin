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
    private static final IOFileFilter ALL_FOLDERS = CanReadFileFilter.CAN_READ;

    @Override
    public Collection<File> getFiles(String folderName) {
        return FileUtils.listFiles(new File(folderName), ALL_FILES, ALL_FOLDERS);
    }
    
    @Override
    public String getAbsoluteFileName(String relativeFolderName) {
        return new File(relativeFolderName).getAbsolutePath();
    }
}
