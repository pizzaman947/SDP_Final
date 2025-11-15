package adapter;

public class ExternalTranslateAPI {
    public String externalTranslate(String input, String source, String target) {
        if ("apple".equalsIgnoreCase(input))
            return "яблоко";
        if ("dog".equalsIgnoreCase(input))
            return "собака";
        return "[" + source + "→" + target + "] " + input.toUpperCase();
    }
}