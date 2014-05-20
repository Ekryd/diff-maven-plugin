package diff.fileset.equalizer;

import diff.parameters.CaseSensitivity;

/**
 * @author bjorn
 * @since 2014-05-20
 */
public class EqualizerFactory {

    public static final EqualizerGeneratesException EQUALIZER_GENERATES_EXCEPTION = new EqualizerGeneratesException();
    public static final EqualizerUsesFileSize EQUALIZER_USES_FILE_SIZE = new EqualizerUsesFileSize();

    public Equalizer createEqualizerUsesFileName(CaseSensitivity caseSensitivity) {
        return new EqualizerUsesFileName(caseSensitivity);
    }

    public Equalizer createEqualizerGeneratesException() {
        return EQUALIZER_GENERATES_EXCEPTION;
    }

    public Equalizer createEqualizerUsesFileSize() {
        return EQUALIZER_USES_FILE_SIZE;
    }
}
