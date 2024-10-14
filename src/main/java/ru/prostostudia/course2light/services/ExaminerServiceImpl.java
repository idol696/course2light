package ru.prostostudia.course2light.services;

import org.springframework.stereotype.Service;
import ru.prostostudia.course2light.Question;
import ru.prostostudia.course2light.exceptions.QuestionBadRequestException;
import ru.prostostudia.course2light.exceptions.QuestionOverloadException;
import ru.prostostudia.course2light.interfaces.ExaminerService;
import ru.prostostudia.course2light.interfaces.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questions;

    public ExaminerServiceImpl(QuestionService questions) {
        this.questions = questions;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0) throw new QuestionBadRequestException();
        Set<Question> setQuestions = new HashSet<>();
        int questionSize = questions.getAll().size();
        if (questionSize < amount) throw new QuestionOverloadException();

        do {
            setQuestions.add(questions.getRandomQuestion());
        } while (setQuestions.size() < amount);

        return setQuestions;
    }
}
