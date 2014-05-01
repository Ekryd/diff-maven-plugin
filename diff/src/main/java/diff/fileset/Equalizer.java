package diff.fileset;

/**
 * @author bjorn
 * @since 2014-05-01
 */
public interface Equalizer {
    boolean equalTo(FileWrapper current, FileWrapper other);
}
