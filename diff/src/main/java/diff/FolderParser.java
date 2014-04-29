package diff;

import diff.files.FileUtil;
import diff.files.FileUtilImpl;
import diff.fileset.FileSet;
import diff.parameters.FileParameters;
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
    private FileUtil fileUtil;
    private String newFolder;
    private String oldFolder;

    private FileSet oldFiles;
    private FileSet newFiles;
    private Letters letters;

    public void setup(PluginParameters parameters) {
        newFolder = parameters.getNewFolder();
        oldFolder = parameters.getOldFolder();
        letters = parameters.getLetters();
        fileUtil = new FileUtilImpl();
    }

    public void diff() {
        oldFiles = createFileSetFromFolder(oldFolder);
        newFiles = createFileSetFromFolder(newFolder);
    }

    private FileSet createFileSetFromFolder(String folder) {
        String absoluteFolderFileName = fileUtil.getAbsoluteFileName(folder);
        Collection<File> files = fileUtil.getFiles(folder);
        
        FileSet fileSet = new FileSet(new FileParameters(letters, absoluteFolderFileName));
        fileSet.setFiles(files);
        
        return fileSet;
    }

    public List<String> getFilesToRemove() {
        FileSet filesToRemove = oldFiles.removeAll(newFiles);

        return filesToRemove.getRelativeFileNames();
    }
}
