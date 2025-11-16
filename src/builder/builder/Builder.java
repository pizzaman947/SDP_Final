package builder.builder;

import decorator.component.Lesson;
import observer.subject.Course;
import java.util.List;

public interface Builder {
    Builder addLesson(Lesson lesson);
    Builder addLessons(List<Lesson> lessons);
    Course build();
}
