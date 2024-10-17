package ru.prostostudia.course2light;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.prostostudia.course2light.exceptions.QuestionBadRequestException;
import ru.prostostudia.course2light.exceptions.QuestionStringIsEmptyException;
import ru.prostostudia.course2light.services.JavaQuestionService;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionServiceTest {
    private final JavaQuestionService questionService = new JavaQuestionService();

    @Test
    @DisplayName("Метод add(Question)- добавление через объект")
    void testAddQuestion_ObjectQuestion() {
        Question question = new Question("Что такое Java?", "Язык программирования");
        questionService.add(question);

        assertEquals(1, questionService.getAll().size());
    }

    @Test
    @DisplayName("Метод add(String,String)- добавление через String")
    void testAddQuestion_StringQuestion() {
        questionService.add("Что такое Java", "Это муки");

        assertEquals(1, questionService.getAll().size());
    }

    @ParameterizedTest
    @DisplayName("Метод add(Question)- добавление через объект")
    @ValueSource(strings = {"Что такое ООП?", "Что такое encapsulation?"})
    void testAddMultipleQuestions(String questionText) {
        Question question = new Question(questionText, "Ответ");
        questionService.add(question);

        assertTrue(questionService.getAll().stream()
                .anyMatch(q -> q.getQuestion().equals(questionText) && q.getAnswer().equals("Ответ")));
    }

    @ParameterizedTest
    @DisplayName("Метод add(String,String) - комплексный тест")
    @MethodSource({"addParameters"})
    void testAddEmployee_deleteEmployee(String message,String question, String answer, boolean testPositive) {

            if (!testPositive) {
                assertThrows(QuestionStringIsEmptyException.class, () -> questionService.add(question, answer));
            } else {
                questionService.add(question,answer);
                assertTrue(questionService.getAll().stream()
                        .anyMatch(q -> q.getQuestion().equals(question) && q.getAnswer().equals(answer)));
            }

    }

    @Test
    @DisplayName("Метод remove - проверка всех типов удаления и контроль возвращаемого значения")
    void testRemoveByStringParametersSuccess() {
        Question question = new Question("Что такое Java?", "Язык программирования");
        questionService.add(question);
        Question removed = questionService.remove("Что такое Java?", "Язык программирования");

        assertEquals(question, removed,"Не совпадает возвращаемое значение удаленного");
        assertFalse(questionService.getAll().contains(question),"Удаление не было успешно проведено");
    }

    @Test
    @DisplayName("Метод remove(String,String) - удаление из пустой коллекции")
    void testRemoveByStringParametersThrowsException() {
        assertThrows(QuestionBadRequestException.class, () ->
                questionService.remove("Несуществующий вопрос", "Несуществующий ответ"));

    }

    @Test
    @DisplayName("Метод remove(Question) - удаление и возвращаемый результат")
    void testRemoveByQuestionObjectSuccess() {
        Question question = new Question("Что такое ООП?", "Принцип программирования");
        questionService.add(question);
        Question removed = questionService.remove(question);

        assertEquals(question, removed);
        assertFalse(questionService.getAll().contains(question));
    }


    @Test
    @DisplayName("Метод remove(String,String) - удаление и возвращаемый результат")
    void testRemoveByQuestionStringSuccess() {
        Question question = new Question("Что такое ООП?", "Принцип программирования");
        questionService.add(question);
        Question removed = questionService.remove("Что такое ООП?","Принцип программирования");

        assertEquals(question, removed);
        assertFalse(questionService.getAll().contains(question));
    }

    @Test
    @DisplayName("Метод remove(Question) - удаление из пустой коллекции")
    void testRemoveByQuestionObjectThrowsException() {
        Question question = new Question("Несуществующий вопрос", "Несуществующий ответ");

        assertThrows(QuestionBadRequestException.class, () -> questionService.remove(question));
    }

    private static Stream<Arguments> addParameters() {
        return Stream.of(
                Arguments.of("Добавление с английским", "Вопрос Question", "Answer", true),
                Arguments.of("Добавление с псевдосимволами", "Вопрос_%:%;№;\"%:**ОУ", "ANСв'ер?:!\"№)(*??:",  true),
                Arguments.of("Добавление цифр", "12387126786", "12341234", true),
                Arguments.of("Тест исключения: Пустое поле вопроса", "", "Ответ", false),
                Arguments.of("Тест исключения: Пустое поле ответа", "Вопрос", "", false),
                Arguments.of("Тест исключения: Пустые поля", "", "", false)
        );

    }
}

