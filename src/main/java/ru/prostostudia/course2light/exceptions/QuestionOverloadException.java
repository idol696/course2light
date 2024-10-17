package ru.prostostudia.course2light.exceptions;

public class QuestionOverloadException extends RuntimeException {
    public QuestionOverloadException() {
        super("QuestionsOverload");
    }
}
