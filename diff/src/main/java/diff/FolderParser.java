package diff;

import diff.files.FileUtil;
import diff.files.FileUtilImpl;
import diff.parameters.PluginParameters;

import java.io.File;
import java.util.ArrayList;
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
    private List<String> oldFiles;
    private List<String> newFiles;

    public void setup(PluginParameters parameters) {
        newFolder = parameters.getNewFolder();
        oldFolder = parameters.getOldFolder();
        fileUtil = new FileUtilImpl();
    }

    public void diff() {
        oldFiles = getRelativeFileNames(oldFolder);
        newFiles = getRelativeFileNames(newFolder);
    }

    private List<String> getRelativeFileNames(String folder) {
        String absoluteFolderFileName = fileUtil.getAbsoluteFileName(folder);
        Collection<File> files = fileUtil.getFiles(folder);

        List<String> fileNames = new ArrayList<String>();
        for (File file : files) {
            String absolutePath = file.getAbsolutePath();
            String relativePath = absolutePath.replaceFirst(absoluteFolderFileName, "");
            if (relativePath.startsWith("/") || relativePath.startsWith("\\")) {
                relativePath = relativePath.substring(1);
            }
            fileNames.add(relativePath);
        }
        
        return fileNames;

    }
    
    public List<String> getFilesToRemove() {
        List<String> filesToRemove = new ArrayList<String>(oldFiles);
        filesToRemove.removeAll(newFiles);
        
        return filesToRemove;
    }
}
