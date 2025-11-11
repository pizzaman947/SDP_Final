package main;
import adapter.ExternalTranslator;
import adapter.TranslationService;
import adapter.TranslatorAdapter;

import decorator.Lesson;
import decorator.BasicLesson;
import decorator.LessonDecorator;
import decorator.TimedLessonDecorator;
import decorator.AudioLessonDecorator;

public class Main {
    public static void main(String[] args) {
        Lesson simpleLesson = new BasicLesson();
        Lesson timedLesson = new TimedLessonDecorator(simpleLesson);
        Lesson timedAudioLesson = new AudioLessonDecorator(timedLesson);

        System.out.println("--- Simple Lesson ---");
        simpleLesson.start();
        System.out.println();

        System.out.println("--- Timed Lesson ---");
        timedLesson.start();
        System.out.println();

        System.out.println("--- Timed + Audio Lesson ---");
        timedAudioLesson.start();
    }
}
