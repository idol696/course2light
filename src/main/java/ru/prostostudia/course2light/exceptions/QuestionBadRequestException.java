package ru.prostostudia.course2light.exceptions;

public class QuestionBadRequestException extends RuntimeException{
    public QuestionBadRequestException(){
        super("BadRequest");
    }
}
