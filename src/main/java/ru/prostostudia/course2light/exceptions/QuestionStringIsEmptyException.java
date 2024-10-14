package ru.prostostudia.course2light.exceptions;

public class QuestionStringIsEmptyException extends RuntimeException{
    public QuestionStringIsEmptyException() {
        super("StringIsEmpty");
    }
}
