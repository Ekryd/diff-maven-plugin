package diff.fileset;

import java.io.File;

/**
 * @author bjorn
 * @since 2014-04-29
 */
class FileWrapper {
    private final FileWrapperBehaviour fileWrapperBehaviour;
    private final File file;
    private final String relativePathName;

    public FileWrapper(FileWrapperBehaviour fileWrapperBehaviour, File file) {
        this.file = file;
        this.fileWrapperBehaviour = fileWrapperBehaviour;
        this.relativePathName = findRelativePathName(fileWrapperBehaviour.getScanBaseFolderPathName(), file);
    }

    private String findRelativePathName(String scanBaseFolderPathName, File file) {
        String absolutePath = file.getAbsolutePath();
        String relativePath = absolutePath.replace(scanBaseFolderPathName, "");
        if (relativePath.startsWith("/") || relativePath.startsWith("\\")) {
            relativePath = relativePath.substring(1);
        }
        return relativePath;
    }

    @Override
    public boolean equals(Object other) {
        return fileWrapperBehaviour.getEqualizer().equalTo(this, (FileWrapper) other);
    }

    @Override
    public int hashCode() {
        return relativePathName.toLowerCase().hashCode();
    }

    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

    public String getRelativePathName() {
        return relativePathName;
    }
}
