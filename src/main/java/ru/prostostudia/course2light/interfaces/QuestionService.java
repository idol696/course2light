package ru.prostostudia.course2light.interfaces;

import ru.prostostudia.course2light.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);
    Question add(Question question);
    Question remove(String question, String answer);
    Question remove(Question question);
    Collection<Question> getAll();
    Question getRandomQuestion();
}
