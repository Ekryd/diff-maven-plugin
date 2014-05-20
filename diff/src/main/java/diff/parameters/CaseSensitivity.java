package diff.parameters;

/**
 * @author bjorn
 * @since 2014-04-30
 */
public enum CaseSensitivity {
    CASE_SENSITIVE, CASE_INSENSITIVE;

    static CaseSensitivity fromString(String caseSensitivityString) {
        for (CaseSensitivity caseSensitivity : values()) {
            if (caseSensitivity.name().equalsIgnoreCase(caseSensitivityString)) {
                return caseSensitivity;
            }
        }
        throw new RuntimeException("Case sensitivity must be either CASE_SENSITIVE or CASE_INSENSITIVE. Was: " + caseSensitivityString);
    }
}
