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
    private final FileUtil fileUtil;
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
        writeParametersToDebugLog(parameters);
        this.parameters = parameters;
        this.newFolder = parameters.getNewFolder();
        this.oldFolder = parameters.getOldFolder();
        this.caseSensitivity = parameters.getCaseSensitivity();
    }

    private void writeParametersToDebugLog(PluginParameters parameters) {
        if (logger.isDebug()) {
            logger.debug(parameters.toString());
        }
    }

    public void diff() {
        oldFiles = createFileSetFromFolder(oldFolder);
        newFiles = createFileSetFromFolder(newFolder);
        logProgress();
    }

    private void logProgress() {
        if (oldFiles.size() == 0 && newFiles.size() == 0) {
            logger.warn("Both old folder and new folder was empty");
        } else {
            logger.info(String.format("Comparing %d old files with %d new files",
                    oldFiles.size(), newFiles.size()));
        }
    }

    private FileSet createFileSetFromFolder(String relativeBaseFolder) {
        String absoluteBaseFolder = fileUtil.getAbsoluteFileName(relativeBaseFolder);

        FolderFilter folderFilter = new FolderFilter(logger, parameters, absoluteBaseFolder);
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
