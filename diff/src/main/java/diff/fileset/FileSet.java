package diff.fileset;

import diff.parameters.Letters;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FileSet {

    private final FileWrapperBehaviour fileWrapperBehaviour;
    private Collection<FileWrapper> fileWrappers = Collections.emptyList();

    public FileSet(Letters letters, String absoluteBaseFolder) {
        this.fileWrapperBehaviour = new FileWrapperBehaviour(letters, absoluteBaseFolder);
    }

    public FileSet setFiles(Collection<File> files) {
        fileWrappers = new ArrayList<FileWrapper>();
        for (File file : files) {
            fileWrappers.add(new FileWrapper(fileWrapperBehaviour, file));
        }

        return this;
    }

    public int size() {
        return fileWrappers.size();
    }

    public Collection<String> getAbsoluteFilePaths() {
        ArrayList<String> returnValue = new ArrayList<String>();

        for (FileWrapper fileWrapper : fileWrappers) {
            returnValue.add(fileWrapper.getAbsolutePath());
        }

        return returnValue;
    }

    public FileSet removeAll(FileSet fileSet) {
        Collection<FileWrapper> filesLeft = new ArrayList<FileWrapper>(fileWrappers);
        fileWrapperBehaviour.setFileNameEqualizer();

        filesLeft.removeAll(fileSet.fileWrappers);

        FileSet returnValue = new FileSet(fileWrapperBehaviour.getLetters(), fileWrapperBehaviour.getScanBaseFolderPathName());
        returnValue.fileWrappers = filesLeft;

        fileWrapperBehaviour.setDefaultEqualizer();
        return returnValue;
    }

    public List<String> getRelativeFileNames() {
        List<String> fileNames = new ArrayList<String>();
        for (FileWrapper fileWrapper : fileWrappers) {
            fileNames.add(fileWrapper.getRelativePathName());
        }

        return fileNames;

    }

}
