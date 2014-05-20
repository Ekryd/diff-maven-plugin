package diff.fileset;

class FileWrapperPair {
    private final FileWrapper oldFile;
    private final FileWrapper newFile;

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
