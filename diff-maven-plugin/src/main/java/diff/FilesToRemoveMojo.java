package diff;

import diff.parameters.PluginParameters;
import diff.parameters.PluginParametersBuilder;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

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
    @Parameter(property = "diff.letters", defaultValue = "CASE_INSENSITIVE")
    private String letters;

    /** Exclude the following folders (and subfolders) from the comparison */
    @Parameter(property = "diff.excludeRelativeFolders")
    private String[] excludeRelativeFolders;

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
        PluginParameters pluginParameters = new PluginParametersBuilder()
                .setFolders(oldFolder, newFolder)
                .setLetterHandling(letters)
                .setExcludeRelativeFolders(excludeRelativeFolders)
                .createPluginParameters();

        folderParser = new FolderParser();
        folderParser.setup(pluginParameters);
    }

    void showFilesToRemove() throws MojoFailureException {
        folderParser.diff();

        List<String> filesToRemove = folderParser.getFilesToRemove();
        if (filesToRemove.size() != 0) {
            getLog().info("The following files should be removed: ");
            for (String fileToRemove : filesToRemove) {
                getLog().info(fileToRemove);
            }
        }
    }

}
