package decorator.decorator;

import decorator.component.Lesson;

public class ChatDecorator extends LessonDecorator {
    private String chatProvider;

    public ChatDecorator(Lesson lesson, String chatProvider) {
        super(lesson);
        this.chatProvider = chatProvider;
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Chat with teacher enabled (" + chatProvider + ")");
    }

    public String getChatProvider() { return chatProvider; }
    public void setChatProvider(String provider) { this.chatProvider = provider; }
}
