package adapter;


public class ExternalTranslatorAdapter implements Translator {
    private ExternalTranslateAPI api;

    public ExternalTranslatorAdapter(ExternalTranslateAPI api) { this.api = api; }

    @Override
    public String translate(String text, String fromLang, String toLang) {
        return api.externalTranslate(text, fromLang, toLang);
    }
}