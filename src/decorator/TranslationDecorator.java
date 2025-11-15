package decorator;
import adapter.*;
public class TranslationDecorator extends LessonDecorator {
    private Translator translator;
    private String word;
    private String fromLang;
    private String toLang;
    public TranslationDecorator(Lesson lesson, Translator translator, String word, String fromLang, String toLang) {
        super(lesson);
        this.translator = translator;
        this.word = word;
        this.fromLang = fromLang;
        this.toLang = toLang;
    }
    @Override
    public void start() {
        super.start();
        System.out.println("Перевод слова: " +
                translator.translate(word, fromLang, toLang));
    }
}
