package diff.parameters;

/**
 * @author bjorn
 * @since 2014-04-30
 */
public enum Letters {
    CASE_SENSITIVE, CASE_INSENSITIVE;
    
    public static Letters fromString(String letters) {
        Letters[] values = values();
        for (Letters value : values) {
            if (value.name().equalsIgnoreCase(letters)) {
                return value;
            }
        }
        throw new RuntimeException("Letter handling must be either CASE_SENSITIVE or CASE_INSENSITIVE");
    }
}
