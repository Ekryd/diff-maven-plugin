package diff.fileset;

import diff.parameters.FileParameters;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FileSet {

    private final FileParameters fileParameters;

    private Collection<FileWrapper> fileWrappers = Collections.emptyList();
 
    public FileSet(FileParameters fileParameters) {
        this.fileParameters = fileParameters;
    }

    public FileSet setFiles(Collection<File> files) {
        fileWrappers = new ArrayList<FileWrapper>();
        for (File file : files) {
            fileWrappers.add(new FileWrapper(fileParameters, file));
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

        filesLeft.removeAll(fileSet.fileWrappers);

        FileSet returnValue = new FileSet(fileParameters);
        returnValue.fileWrappers = filesLeft;

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
