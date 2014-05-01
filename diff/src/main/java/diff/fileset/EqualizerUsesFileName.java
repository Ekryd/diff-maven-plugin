package diff.fileset;

import diff.parameters.Letters;

import static diff.parameters.Letters.CASE_SENSITIVE;

/**
 * @author bjorn
 * @since 2014-05-01
 */
class EqualizerUsesFileName implements Equalizer {
    private final Letters letters;

    public EqualizerUsesFileName(Letters letters) {
        this.letters = letters;
    }

    @Override
    public boolean equalTo(FileWrapper current, FileWrapper other) {
        if (current == other) {
            return true;
        }

        if (letters == CASE_SENSITIVE) {
            return current.getRelativePathName().equals(other.getRelativePathName());
        }

        return current.getRelativePathName().equalsIgnoreCase(other.getRelativePathName());        
    }
}
