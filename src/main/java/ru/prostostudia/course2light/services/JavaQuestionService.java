package ru.prostostudia.course2light.services;

import org.springframework.stereotype.Service;
import ru.prostostudia.course2light.Question;
import ru.prostostudia.course2light.exceptions.QuestionBadRequestException;
import ru.prostostudia.course2light.interfaces.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    public static final Random RANDOM = new Random();

    @Override
    public Question add(String question, String answer) {

        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (questions.add(question)) {
            return question;
        }
        throw new QuestionBadRequestException();
    }

    @Override
    public Question remove(String question, String answer) {
        return remove(new Question(question, answer));
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            return question;
        }
        throw new QuestionBadRequestException();
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        int numberQuestion = !questions.isEmpty() ? RANDOM.nextInt(questions.size()) : 0;
        return questions.stream().toList().get(numberQuestion);
    }
}
