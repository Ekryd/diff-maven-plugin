package diff.fileset;

import diff.parameters.FileParameters;

import java.io.File;

import static diff.parameters.Letters.CASE_SENSITIVE;

/**
 * @author bjorn
 * @since 2014-04-29
 */
class FileWrapper {
    private final FileParameters fileParameters;
    private final File file;
    private final String relativePathName;

    public FileWrapper(FileParameters fileParameters, File file) {
        this.file = file;
        this.fileParameters = fileParameters;
        this.relativePathName = findRelativePathName(fileParameters.getScanBaseFolderPathName(), file);
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileWrapper that = (FileWrapper) o;

        if (fileParameters.getLetters() == CASE_SENSITIVE) {
            return relativePathName.equals(that.relativePathName);
        }

        return relativePathName.equalsIgnoreCase(that.relativePathName);
    }

    @Override
    public int hashCode() {
        return file.hashCode();
    }

    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

    public String getRelativePathName() {
        return relativePathName;
    }
}
