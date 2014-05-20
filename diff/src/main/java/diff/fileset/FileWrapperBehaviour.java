package diff.fileset;

import diff.parameters.CaseSensitivity;

/**
 * @author bjorn
 * @since 2014-04-29
 */
class FileWrapperBehaviour {

    private final CaseSensitivity caseSensitivity;
    private final String scanBaseFolderPathName;
    private Equalizer equalizer = new EqualizerGeneratesException();

    public FileWrapperBehaviour(CaseSensitivity caseSensitivity, String scanBaseFolderPathName) {
        this.caseSensitivity = caseSensitivity;
        this.scanBaseFolderPathName = scanBaseFolderPathName;
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
        equalizer = new EqualizerUsesFileName(caseSensitivity);
    }

    public void setDefaultEqualizer() {
        equalizer = new EqualizerGeneratesException();
    }

    public void setFileSizeEqualizer() {
        equalizer = new EqualizerUsesFileSize();
    }
}
