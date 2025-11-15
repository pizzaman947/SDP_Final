package main;

import decorator.*;
import adapter.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] levels = {"Beginner", "Intermediate", "Advanced"};
        String[][] videoTopics = {
                { // Beginner
                        "Глагол to be",
                        "Артикли a/an, the",
                        "Порядок слов",
                        "Вопросы с what, who, where, when, why, how, how much/many",
                        "Единственное и множественное число существительных (-s, -es)"
                },
                { // Intermediate
                        "Глаголы состояния",
                        "Определительные придаточные предложения с that, who, which, where",
                        "Герундий",
                        "Модальные глаголы have to, don't have to, must, mustn't, should, shouldn't, may, might, might not",
                        "Условные предложения первого, второго и третьего типа"
                },
                { // Advanced
                        "Модальные глаголы в прошедшем времени",
                        "Смешанный тип условных предложений",
                        "Инверсия",
                        "Побудительные обороты с get и have (казуатив)",
                        "Пунктуация"
                }
        };
        String[][] videoUrls = {
                { // Beginner
                        "https://youtube.com/A1_1", "https://youtube.com/A1_2", "https://youtube.com/A1_3",
                        "https://youtube.com/A1_4", "https://youtube.com/A1_5"
                },
                { // Intermediate
                        "https://youtube.com/B1_1", "https://youtube.com/B1_2", "https://youtube.com/B1_3",
                        "https://youtube.com/B1_4", "https://youtube.com/B1_5"
                },
                { // Advanced
                        "https://youtube.com/C1_1", "https://youtube.com/C1_2", "https://youtube.com/C1_3",
                        "https://youtube.com/C1_4", "https://youtube.com/C1_5"
                }
        };

        TeacherLesson teacherLesson = new TeacherLesson("Speaking", "Alex Johnson");
        ExternalTranslateAPI extAPI = new ExternalTranslateAPI();
        Translator translator = new ExternalTranslatorAdapter(extAPI);

        boolean working = true;
        while (working) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1 — Видео урок (выбор уровня и темы)");
            System.out.println("2 — Урок с учителем (" +
                    teacherLesson.getTopic() + ", " + teacherLesson.getTeacherName() + ")");
            System.out.println("3 — Перевести слово (en→es)");
            System.out.println("4 — Выход");
            System.out.print("Ваш выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Уровни
                    System.out.println("\nВыберите уровень:");
                    for (int i = 0; i < levels.length; i++) System.out.println((i + 1) + " — " + levels[i]);
                    System.out.print("Номер уровня: "); int levelChoice = scanner.nextInt() - 1; scanner.nextLine();
                    if (levelChoice < 0 || levelChoice >= levels.length) { System.out.println("Нет такого уровня."); break; }
                    // Темы
                    System.out.println("\nТемы для " + levels[levelChoice] + ":");
                    for (int i = 0; i < videoTopics[levelChoice].length; i++)
                        System.out.println((i + 1) + " — " + videoTopics[levelChoice][i]);
                    System.out.print("Выберите номер темы: "); int topicChoice = scanner.nextInt() - 1; scanner.nextLine();
                    if (topicChoice < 0 || topicChoice >= videoTopics[levelChoice].length) {
                        System.out.println("Нет такого номера."); break;
                    }
                    VideoLesson videoLesson = new VideoLesson(
                            videoTopics[levelChoice][topicChoice],
                            videoUrls[levelChoice][topicChoice]);
                    System.out.println("\n--- Ваш урок ---");
                    videoLesson.start();
                    break;
                case 2:
                    teacherLesson.start();
                    break;
                case 3:
                    System.out.print("Введите слово на английском: ");
                    String word = scanner.nextLine();
                    String translated = translator.translate(word, "en", "es");
                    System.out.println("Перевод: " + translated);
                    break;
                case 4:
                    working = false;
                    System.out.println("Завершение работы!");
                    break;


                    //strategy tomi
                case 5: {
                    LanguageLearningApp app = new LanguageLearningApp();

                    System.out.println("\nchoose language:");
                    System.out.println("1 — English");
                    System.out.println("2 — Russian");
                    System.out.println("3 — Kazakh");
                    System.out.println("4 — Chinese");

                    int langChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (langChoice) {
                        case 1 -> app.setLanguage(new English());
                        case 2 -> app.setLanguage(new Russian());
                        case 3 -> app.setLanguage(new Kazakh());
                        case 4 -> app.setLanguage(new Chinese());
                        default -> System.out.println("we dont have this language yet.");
                    }

                    System.out.println("\nlearning method:");
                    System.out.println("1 — Video lesson");
                    System.out.println("2 — Audio practice");
                    System.out.println("3 — Reading articles");
                    System.out.println("4 — Chat with native");

                    int method = scanner.nextInt();
                    scanner.nextLine();

                    switch (method) {
                        case 1 -> app.setLearningStrategy(new VideoLearning());
                        case 2 -> app.setLearningStrategy(new AudioPractice());
                        case 3 -> app.setLearningStrategy(new ReadingArticles());
                        case 4 -> app.setLearningStrategy(new ChatWithNative());
                        default -> System.out.println("we dont have this method yet.");
                    }

                    System.out.print("\nchoose the topic: ");
                    String topic = scanner.nextLine();

                    app.startLearning(topic);
                    break;
                }

                //builder pattern (tomi)
                case 6: {
                    CourseBuilder builder = new CourseBuilder();

                    System.out.print("\nchoose course language: ");
                    String lang = scanner.nextLine();

                    System.out.print("enter ur level;: ");
                    String lvl = scanner.nextLine();

                    builder.setLanguage(lang)
                            .setLevel(lvl);

                    System.out.println("enter topics (and 'stop' to quit):");
                    while (true) {
                        String t = scanner.nextLine();
                        if (t.equalsIgnoreCase("stop")) break;
                        builder.addTopic(t);
                    }

                    System.out.println("choose method (same thing with stop):");
                    while (true) {
                        String m = scanner.nextLine();
                        if (m.equalsIgnoreCase("stop")) break;
                        builder.addMethod(m);
                    }

                    Course course = builder.build();
                    course.show();
                    break;
                }

                default:
                    System.out.println("Нет такого пункта.");
            }
        }
        scanner.close();
    }
}
