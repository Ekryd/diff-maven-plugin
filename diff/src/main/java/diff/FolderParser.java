package diff;

import diff.files.FileUtil;
import diff.files.FileUtilImpl;
import diff.files.FolderFilter;
import diff.fileset.FileSet;
import diff.logger.PluginLogger;
import diff.parameters.CaseSensitivity;
import diff.parameters.PluginParameters;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * @author bjorn
 * @since 2014-04-25
 */
class FolderParser {
    private final PluginLogger logger;
    private PluginParameters parameters;
    private FileUtil fileUtil;
    private String newFolder;
    private String oldFolder;

    private FileSet oldFiles;
    private FileSet newFiles;
    private CaseSensitivity caseSensitivity;

    public FolderParser(PluginLogger logger) {  
        this.logger = logger;
        this.fileUtil = new FileUtilImpl(logger);
    }

    public void setup(PluginParameters parameters) {
        this.parameters = parameters;
        this.newFolder = parameters.getNewFolder();
        this.oldFolder = parameters.getOldFolder();
        this.caseSensitivity = parameters.getCaseSensitivity();
    }

    public void diff() {
        oldFiles = createFileSetFromFolder(oldFolder);
        newFiles = createFileSetFromFolder(newFolder);
    }

    private FileSet createFileSetFromFolder(String relativeBaseFolder) {
        String absoluteBaseFolder = fileUtil.getAbsoluteFileName(relativeBaseFolder);
               
        FolderFilter folderFilter = new FolderFilter(parameters, absoluteBaseFolder);
        FileSet fileSet = new FileSet(caseSensitivity, absoluteBaseFolder);
        
        Collection<File> files = fileUtil.getFiles(relativeBaseFolder, folderFilter);
        fileSet.setFiles(files);

        return fileSet;
    }

    public void outputFilesToRemove() {
        List<String> filesToRemove = getFilesToRemove();
        if (filesToRemove.size() != 0) {
            logger.info("The following files should be removed: ");
            for (String fileToRemove : filesToRemove) {
                logger.info(fileToRemove);
            }
        }
    }

    List<String> getFilesToRemove() {
        FileSet filesToRemove = oldFiles.removeAll(newFiles);

        return filesToRemove.getRelativeFileNames();
    }

    public Iterable<? extends String> getFilesChanged() {
        FileSet filesChanged = oldFiles.retainChangedFiles(newFiles);

        return filesChanged.getRelativeFileNames();
    }
}
