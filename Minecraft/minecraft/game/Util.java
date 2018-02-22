package minecraft.game;

public class Util {
    private static final String line = "--------------------------------------------------------------";

    public static String line(boolean newlineBefore, boolean newlineAfter) {
        return (newlineBefore ? "\n" : "") + line + (newlineAfter ? "\n" : "");
    }

    public static String line(boolean newlines) {
        return line(newlines, newlines);
    }

    public static String line() {
        return line(false);
    }

    public static String wrap(Object object, boolean newlineBefore, boolean newlineAfter) {
        return line(newlineBefore, true) + object + line(true, newlineAfter);
    }

    public static String wrap(Object object, boolean newlines) {
        return wrap(object, newlines, newlines);
    }

    public static String wrap(Object object) {
        return wrap(object, false);
    }
}
