package diff.fileset.equalizer;

/**
 * @author bjorn
 * @since 2014-05-01
 */
public interface Equalizer {
    boolean equalTo(FileMethods current, FileMethods other);
}
