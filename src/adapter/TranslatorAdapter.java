package adapter;

public class TranslatorAdapter implements TranslationService {
    private ExternalTranslator externalTranslator;
    public TranslatorAdapter(ExternalTranslator externalTranslator) {
        this.externalTranslator = externalTranslator;
    }
    @Override
    public String getTranslation(String word, String sourceLang, String targetLang) {
        // Адаптация запроса
        return externalTranslator.translateText(word, sourceLang, targetLang);
    }
}
