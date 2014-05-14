package diff.fileset;

import diff.parameters.Letters;

/**
 * @author bjorn
 * @since 2014-04-29
 */
class FileWrapperBehaviour {

    private final Letters letters;
    private final String scanBaseFolderPathName;
    private Equalizer equalizer = new EqualizerGeneratesException();

    public FileWrapperBehaviour(Letters letters, String scanBaseFolderPathName) {
        this.letters = letters;
        this.scanBaseFolderPathName = scanBaseFolderPathName;
    }

    public Letters getLetters() {
        return letters;
    }

    public String getScanBaseFolderPathName() {
        return scanBaseFolderPathName;
    }

    public Equalizer getEqualizer() {
        return equalizer;
    }

    public void setFileNameEqualizer() {
        equalizer = new EqualizerUsesFileName(letters);
    }

    public void setDefaultEqualizer() {
        equalizer = new EqualizerGeneratesException();
    }

    public void setFileSizeEqualizer() {
        equalizer = new EqualizerUsesFileSize();
    }
}
