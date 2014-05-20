package diff.files;

import diff.exception.FailureException;
import diff.logger.PluginLogger;
import diff.parameters.CaseSensitivity;
import diff.parameters.PluginParameters;
import org.apache.commons.io.filefilter.AbstractFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author bjorn
 * @since 2014-05-01
 */
public class FolderFilter extends AbstractFileFilter {

    private static final Pattern REGEX_MATCHING_TRIMMING_ANY_SLASHES = Pattern.compile("^[/\\\\]*(.*?)[/\\\\]*$");
    private final List<String> excludeAbsoluteFolders;
    private final CaseSensitivity caseSensitivity;
    private final PluginLogger logger;

    public FolderFilter(PluginLogger logger, PluginParameters parameters, String baseFolder) {
        this.logger = logger;
        this.caseSensitivity = parameters.getCaseSensitivity();
        this.excludeAbsoluteFolders = getExcludedFolders(parameters, baseFolder);
    }

    private List<String> getExcludedFolders(PluginParameters parameters, String baseFolder) {
        List<String> returnValue = new ArrayList<String>();
                
        for (String excludeRelativeFolder : parameters.getExcludeRelativeFolders()) {
            String folderWithoutSlashesBeforeOrAfter = REGEX_MATCHING_TRIMMING_ANY_SLASHES.matcher(excludeRelativeFolder).replaceAll("$1");

            returnValue.add(new File(baseFolder, folderWithoutSlashesBeforeOrAfter).getAbsolutePath());
        }
        
        return returnValue;
    }

    /**
     * Checks to see if the file can be read and handle exclusion filters.
     *
     * @param file the File to check.
     * @return <code>true</code> if the file can be
     * read, otherwise <code>false</code>.
     */
    @Override
    public boolean accept(File file) {
        String absolutePath = file.getAbsolutePath();
        if (!file.canRead()) {
            throw new FailureException("Cannot read folder " + absolutePath);
        }

        if (file.isDirectory()) {
            return acceptDirectory(absolutePath);
        }
        
        return true;
    }

    private boolean acceptDirectory(String absolutePath) {
        for (String excludeAbsoluteFolder : excludeAbsoluteFolders) {
            if (isExcluded(absolutePath, excludeAbsoluteFolder)) {
                if (logger.isDebug()) {
                    logger.debug("Excluding folder " + absolutePath);
                }
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean isExcluded(String absolutePath, String excludeAbsoluteFolder) {
        if (caseSensitivity == CaseSensitivity.CASE_INSENSITIVE && excludeAbsoluteFolder.equalsIgnoreCase(absolutePath)) {
            return true;
        }
        if (caseSensitivity == CaseSensitivity.CASE_SENSITIVE && excludeAbsoluteFolder.equals(absolutePath)) {
            return true;
        }
        return false;
    }
}
