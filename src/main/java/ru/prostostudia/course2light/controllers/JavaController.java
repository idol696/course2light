package ru.prostostudia.course2light.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.prostostudia.course2light.exceptions.QuestionBadRequestException;
import ru.prostostudia.course2light.exceptions.QuestionStringIsEmptyException;
import ru.prostostudia.course2light.interfaces.QuestionService;

import java.util.List;

@RestController
@RequestMapping("exam/java")
public class JavaController {

    private final QuestionService questionService;

    public JavaController(QuestionService questionService) {

        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<?> getQuestions() {
        try {
            return ResponseEntity.ok(questionService.getAll());
        } catch (QuestionBadRequestException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/add")
    public ResponseEntity<?> addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        try {
            return ResponseEntity.ok(questionService.add(question,answer));
        } catch (QuestionBadRequestException | QuestionStringIsEmptyException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/remove")
    public ResponseEntity<?> delQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        try {
            return ResponseEntity.ok(questionService.remove(question,answer));
        } catch (QuestionBadRequestException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
