package diff;

import diff.exception.ExceptionHandler;
import diff.exception.FailureException;
import diff.logger.MavenLogger;
import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Mojo (Maven plugin) that finds if any files should be removed during patch.
 *
 * @author Bjorn Ekryd
 */
@Mojo(name = "filesToRemove",
        threadSafe = true,
        defaultPhase = LifecyclePhase.INSTALL,
        requiresProject = false)
public class FilesToRemoveMojo extends AbstractMojo {
    /** The absolute path to the old folder structure */
    @Parameter(property = "diff.oldFolder", required = true)
    private String oldFolder;

    /** The absolute path to the new folder structure */
    @Parameter(property = "diff.newFolder", required = true)
    private String newFolder;

    /** If folder and file comparison should be case sensitive or not */
    @Parameter(property = "diff.caseSensitivity", defaultValue = "CASE_INSENSITIVE")
    private String caseSensitivity;

    /** Exclude the following folders (and subfolders) from the comparison */
    @Parameter(property = "diff.excludeRelativeFolders")
    private String[] excludeRelativeFolders;

    /** Store the result from fileToRemove in this file (if specified) */
    @Parameter(property = "diff.filesToRemoveOutputFile")
    private File filesToRemoveOutputFile;

    private FolderParser folderParser;

    /**
     * Execute plugin.
     *
     * @throws org.apache.maven.plugin.MojoFailureException exception that will be handled by plugin framework
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    @Override
    public void execute() throws MojoFailureException {
        setup();
        showFilesToRemove();
    }

    void setup() throws MojoFailureException {
        try {
            
            PluginParameters pluginParameters = new PluginParametersBuilder()
                    .setFolders(oldFolder, newFolder)
                    .setCaseSensitivity(caseSensitivity)
                    .setExcludeRelativeFolders(excludeRelativeFolders)
                    .setFilesToRemoveOutputFile(filesToRemoveOutputFile)
                    .createPluginParameters();

            folderParser = new FolderParser(new MavenLogger(getLog()));
            folderParser.setup(pluginParameters);
            
        } catch (FailureException e) {
            new ExceptionHandler(e).throwMojoFailureException();
        }
    }

    void showFilesToRemove() throws MojoFailureException {
        try {

        folderParser.diff();
        folderParser.outputFilesToRemove();

        } catch (FailureException e) {
            new ExceptionHandler(e).throwMojoFailureException();
        }
    }

}
