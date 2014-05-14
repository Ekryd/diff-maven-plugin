package diff.fileset;

import diff.parameters.Letters;

import java.io.File;
import java.util.*;

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

    public FileSet removeAll(FileSet newFileSet) {
        Collection<FileWrapper> filesThatAreNotInNewFileSet = removeAllThatDifferByName(newFileSet);

        FileSet returnValue = createNewFileSet(filesThatAreNotInNewFileSet);

        return returnValue;
    }

    private FileSet createNewFileSet(Collection<FileWrapper> newFileSet) {
        FileSet returnValue = new FileSet(fileWrapperBehaviour.getLetters(), fileWrapperBehaviour.getScanBaseFolderPathName());
        returnValue.fileWrappers = newFileSet;
        return returnValue;
    }

    private Collection<FileWrapper> removeAllThatDifferByName(FileSet newFileSet) {
        fileWrapperBehaviour.setFileNameEqualizer();

        Collection<FileWrapper> oldFiles = new ArrayList<FileWrapper>(fileWrappers);
        oldFiles.removeAll(newFileSet.fileWrappers);

        fileWrapperBehaviour.setDefaultEqualizer();
        return oldFiles;
    }

    public List<String> getRelativeFileNames() {
        List<String> fileNames = new ArrayList<String>();
        for (FileWrapper fileWrapper : fileWrappers) {
            fileNames.add(fileWrapper.getRelativePathName());
        }

        return fileNames;

    }

    public FileSet retainChangedFiles(FileSet newFileSet) {
        Collection<FileWrapperPair> filesWithSameName = retainSameFileName(newFileSet);
        
        Collection<FileWrapper> filesThatHaveDiffSize = retainAllThatDifferBySize(filesWithSameName);

        FileSet returnValue = createNewFileSet(filesThatHaveDiffSize);

        return returnValue;
    }

    private Collection<FileWrapperPair> retainSameFileName(FileSet newFileSet) {
        fileWrapperBehaviour.setFileNameEqualizer();
        newFileSet.fileWrapperBehaviour.setFileNameEqualizer();

        Map<FileWrapper, FileWrapper> oldFiles = createMap(fileWrappers);
        Collection<FileWrapperPair> returnValue = new ArrayList<FileWrapperPair>();
        for (FileWrapper newFileWrapper : newFileSet.fileWrappers) {
            if (oldFiles.containsKey(newFileWrapper)) {
                returnValue.add(new FileWrapperPair(oldFiles.get(newFileWrapper), newFileWrapper));
            }
        }

        newFileSet.fileWrapperBehaviour.setDefaultEqualizer();
        fileWrapperBehaviour.setDefaultEqualizer();
        return returnValue;
    }

    private Map<FileWrapper, FileWrapper> createMap(Collection<FileWrapper> fileWrappers) {
        Map<FileWrapper, FileWrapper> returnValue = new LinkedHashMap<FileWrapper, FileWrapper>();
        for (FileWrapper fileWrapper : fileWrappers) {
            returnValue.put(fileWrapper, fileWrapper);
        }
        return returnValue;
    }

    private Collection<FileWrapper> retainAllThatDifferBySize(Collection<FileWrapperPair> filePairs) {
        fileWrapperBehaviour.setFileSizeEqualizer();

        Collection<FileWrapper> returnValue = new ArrayList<FileWrapper>();
        for (FileWrapperPair filePair : filePairs) {
            if (!filePair.equalsEachOther()) {
                returnValue.add(filePair.getNewFile());
            }
        }

        fileWrapperBehaviour.setDefaultEqualizer();
        return returnValue;
    }

}
