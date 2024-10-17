package ru.prostostudia.course2light.services;

import org.springframework.stereotype.Service;
import ru.prostostudia.course2light.interfaces.QuestionService;

@Service
public class TechSupportService {
    private final QuestionService questionService;

    public TechSupportService(QuestionService questions) {
        this.questionService = questions;
    }

    public void demoFill() {
        questionService.add("Вы любите переменные?", "Да");
        questionService.add("Как вам циклы for?", "МИлые");
        questionService.add("Нравится ли вам объектно-ориентированное программирование?", "Да");
        questionService.add("Как вы относитесь к рекурсии?", "Сомнительно");
        questionService.add("Как вам работа с массивами?", "Быстрая");
        questionService.add("Любите ли вы Java?", "Вешаюсь");
        questionService.add("Как вам обработка исключений?", "Ничётак");
        questionService.add("Нравится ли вам структура данных Map?", "Ну,типа, да");
        questionService.add("Как вы относитесь к интерфейсам?", "Не отношусь, пишу");
        questionService.add("Любите ли вы работу с файлами?", "Только в двоичном коде");
    }
}
