package diff.fileset;

/**
 * @author bjorn
 * @since 2014-05-01
 */
class EqualizerUsesFileSize implements Equalizer {
    @Override
    public boolean equalTo(FileWrapper current, FileWrapper other) {
        if (current == other) {
            return true;
        }

        return current.getFileSize() == other.getFileSize();
    }
}
