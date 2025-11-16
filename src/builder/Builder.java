package builder;

import decorator.Lesson;
import observer.Course;
import java.util.List;

public interface Builder {
    Builder addLesson(Lesson lesson);
    Builder addLessons(List<Lesson> lessons);
    Course build();
}
