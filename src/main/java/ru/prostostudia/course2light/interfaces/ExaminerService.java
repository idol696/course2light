package ru.prostostudia.course2light.interfaces;

import ru.prostostudia.course2light.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
