package diff.fileset.equalizer;

/**
 * @author bjorn
 * @since 2014-05-01
 */
class EqualizerGeneratesException implements Equalizer {
    @Override
    public boolean equalTo(FileMethods current, FileMethods other) {
        throw new IllegalStateException("Please set a specific equalizer depending on the operation");
    }
}
