package lotto.common;

public class ErrorMessage {
    private static final String PREFIX = "[ERROR] ";

    public static String of(String message) {
        return PREFIX + message;
    }
}