package diff;

import diff.files.FileUtil;
import diff.files.FileUtilImpl;
import diff.fileset.FileSet;
import diff.files.FolderFilter;
import diff.parameters.Letters;
import diff.parameters.PluginParameters;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * @author bjorn
 * @since 2014-04-25
 */
class FolderParser {
    private PluginParameters parameters;
    private FileUtil fileUtil;
    private String newFolder;
    private String oldFolder;

    private FileSet oldFiles;
    private FileSet newFiles;
    private Letters letters;

    public void setup(PluginParameters parameters) {
        this.parameters = parameters;
        this.newFolder = parameters.getNewFolder();
        this.oldFolder = parameters.getOldFolder();
        this.letters = parameters.getLetters();
        this.fileUtil = new FileUtilImpl();
    }

    public void diff() {
        oldFiles = createFileSetFromFolder(oldFolder);
        newFiles = createFileSetFromFolder(newFolder);
    }

    private FileSet createFileSetFromFolder(String relativeBaseFolder) {
        String absoluteBaseFolder = fileUtil.getAbsoluteFileName(relativeBaseFolder);
               
        FolderFilter folderFilter = new FolderFilter(parameters, absoluteBaseFolder);
        FileSet fileSet = new FileSet(letters, absoluteBaseFolder);
        
        Collection<File> files = fileUtil.getFiles(relativeBaseFolder, folderFilter);
        fileSet.setFiles(files);

        return fileSet;
    }

    public List<String> getFilesToRemove() {
        FileSet filesToRemove = oldFiles.removeAll(newFiles);

        return filesToRemove.getRelativeFileNames();
    }
}
