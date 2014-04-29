package matcher;

import org.hamcrest.Matcher;
import org.hamcrest.core.StringEndsWith;

public class FileSlashMatcher {

    public static Matcher<java.lang.String> fileNameEndsWith(String suffix) {
        return new FileNameEndsWith(suffix);
    }

    private static class FileNameEndsWith extends StringEndsWith {

        public FileNameEndsWith(String substring) {
            super(substring);
        }

        @Override
        protected boolean evalSubstringOf(String s) {
            String replaceBackSlashes = s.replace("\\", "/");
            return replaceBackSlashes.endsWith(substring);
        }
    }
}