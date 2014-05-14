package diff.fileset;

/**
 * Created with IntelliJ IDEA.
 * User: bjoern.ekryd.ext
 * Date: 2014-05-14
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class FileWrapperPair {
    private FileWrapper oldFile;
    private FileWrapper newFile;

    public FileWrapperPair(FileWrapper oldFile, FileWrapper newFile) {
        this.oldFile = oldFile;
        this.newFile = newFile;
    }

    public boolean equalsEachOther() {
        return oldFile.equals(newFile);
    }

    public FileWrapper getOldFile() {
        return oldFile;
    }

    public FileWrapper getNewFile() {
        return newFile;
    }
}
