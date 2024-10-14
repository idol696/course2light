package ru.prostostudia.course2light.services;

import org.springframework.stereotype.Service;
import ru.prostostudia.course2light.Question;
import ru.prostostudia.course2light.exceptions.QuestionBadRequestException;
import ru.prostostudia.course2light.interfaces.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
        if (questions.add(question)) return question;
        else throw new QuestionBadRequestException();
    }

    @Override
    public Question remove(String question, String answer) {
        return remove(new Question(question, answer));
    }

     @Override
    public Question remove(Question question) {
        if(questions.contains(question)) {
            if(questions.remove(question)) {
                return question;
            }
        }
        throw new QuestionBadRequestException();
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        int numberQuestion = !questions.isEmpty() ? RANDOM.nextInt(questions.size()) : 0;
        return questions.stream().toList().get(numberQuestion);
    }

    public void demoFill() {
        add("Вы любите переменные?", "Да");
        add("Как вам циклы for?", "МИлые");
        add("Нравится ли вам объектно-ориентированное программирование?", "Да");
        add("Как вы относитесь к рекурсии?", "Сомнительно");
        add("Как вам работа с массивами?", "Быстрая");
        add("Любите ли вы Java?", "Вешаюсь");
        add("Как вам обработка исключений?", "Ничётак");
        add("Нравится ли вам структура данных Map?", "Ну,типа, да");
        add("Как вы относитесь к интерфейсам?", "Не отношусь, пишу");
        add("Любите ли вы работу с файлами?", "Только в двоичном коде");
    }
}
