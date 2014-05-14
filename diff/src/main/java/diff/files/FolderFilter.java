package diff.files;

import diff.exception.FailureException;
import diff.parameters.Letters;
import diff.parameters.PluginParameters;
import org.apache.commons.io.filefilter.AbstractFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bjorn
 * @since 2014-05-01
 */
public class FolderFilter extends AbstractFileFilter {

    private static final String REGEX_MATCHING_TRIMMING_ANY_SLASHES = "^[/\\\\]*(.*?)[/\\\\]*$";
    private final List<String> excludeAbsoluteFolders = new ArrayList<String>();
    private final Letters letters;

    public FolderFilter(PluginParameters parameters, String baseFolder) {
        letters = parameters.getLetters();

        for (String excludeRelativeFolder : parameters.getExcludeRelativeFolders()) {
            String folderWithoutSlashesBeforeOrAfter = excludeRelativeFolder.replaceAll(REGEX_MATCHING_TRIMMING_ANY_SLASHES, "$1");

            excludeAbsoluteFolders.add(new File(baseFolder, folderWithoutSlashesBeforeOrAfter).getAbsolutePath());
        }
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

        for (String excludeAbsoluteFolder : excludeAbsoluteFolders) {
            if (isExcluded(absolutePath, excludeAbsoluteFolder)) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean isExcluded(String absolutePath, String excludeAbsoluteFolder) {
        if (letters == Letters.CASE_INSENSITIVE && excludeAbsoluteFolder.equalsIgnoreCase(absolutePath)) {
            return true;
        }
        if (letters == Letters.CASE_SENSITIVE && excludeAbsoluteFolder.equals(absolutePath)) {
            return true;
        }
        return false;
    }
}
