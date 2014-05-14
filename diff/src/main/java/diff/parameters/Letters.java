package diff.parameters;

/**
 * @author bjorn
 * @since 2014-04-30
 */
public enum Letters {
    CASE_SENSITIVE, CASE_INSENSITIVE;

    static Letters fromString(String letterString) {
        for (Letters letter : values()) {
            if (letter.name().equalsIgnoreCase(letterString)) {
                return letter;
            }
        }
        throw new RuntimeException("Case sensitivity must be either CASE_SENSITIVE or CASE_INSENSITIVE. Was: " + letterString);
    }
}
