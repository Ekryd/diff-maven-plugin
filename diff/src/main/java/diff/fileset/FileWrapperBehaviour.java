package diff.fileset;

import diff.fileset.equalizer.Equalizer;
import diff.fileset.equalizer.EqualizerFactory;
import diff.parameters.CaseSensitivity;

/**
 * @author bjorn
 * @since 2014-04-29
 */
class FileWrapperBehaviour {

    private final CaseSensitivity caseSensitivity;
    private final String scanBaseFolderPathName;
    private Equalizer equalizer;

    public FileWrapperBehaviour(CaseSensitivity caseSensitivity, String scanBaseFolderPathName) {
        this.caseSensitivity = caseSensitivity;
        this.scanBaseFolderPathName = scanBaseFolderPathName;
        setDefaultEqualizer();
    }

    public CaseSensitivity getCaseSensitivity() {
        return caseSensitivity;
    }

    public String getScanBaseFolderPathName() {
        return scanBaseFolderPathName;
    }

    public Equalizer getEqualizer() {
        return equalizer;
    }

    public void setFileNameEqualizer() {
        equalizer = new EqualizerFactory().createEqualizerUsesFileName(caseSensitivity);
    }

    public void setDefaultEqualizer() {
        equalizer = new EqualizerFactory().createEqualizerGeneratesException();
    }

    public void setFileSizeEqualizer() {
        equalizer = new EqualizerFactory().createEqualizerUsesFileSize();
    }
}
