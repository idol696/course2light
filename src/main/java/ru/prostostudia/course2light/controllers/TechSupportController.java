package ru.prostostudia.course2light.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.prostostudia.course2light.services.TechSupportService;

@RestController
@RequestMapping("/init")
public class TechSupportController {

    private final TechSupportService questionService;

    public TechSupportController(TechSupportService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public String initQuestion() {
        questionService.demoFill();
        return "Заполнено успешно!";
    }
}
