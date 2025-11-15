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
                default:
                    System.out.println("Нет такого пункта.");
            }
        }
        scanner.close();
    }
}
