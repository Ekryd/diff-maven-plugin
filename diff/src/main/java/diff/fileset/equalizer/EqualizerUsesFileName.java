package diff.fileset.equalizer;

import diff.parameters.CaseSensitivity;

import static diff.parameters.CaseSensitivity.CASE_SENSITIVE;

/**
 * @author bjorn
 * @since 2014-05-01
 */
class EqualizerUsesFileName implements Equalizer {
    private final CaseSensitivity caseSensitivity;

    public EqualizerUsesFileName(CaseSensitivity caseSensitivity) {
        this.caseSensitivity = caseSensitivity;
    }

    @Override
    public boolean equalTo(FileMethods current, FileMethods other) {
        if (current == other) {
            return true;
        }

        if (caseSensitivity == CASE_SENSITIVE) {
            return current.getRelativePathName().equals(other.getRelativePathName());
        }

        return current.getRelativePathName().equalsIgnoreCase(other.getRelativePathName());
    }
}
