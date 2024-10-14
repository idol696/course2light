package ru.prostostudia.course2light;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.prostostudia.course2light.exceptions.QuestionBadRequestException;
import ru.prostostudia.course2light.exceptions.QuestionOverloadException;
import ru.prostostudia.course2light.services.ExaminerServiceImpl;
import ru.prostostudia.course2light.services.JavaQuestionService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExaminerServiceTest {

    @Mock
    private JavaQuestionService questionService;  // Мокаем зависимость

    @InjectMocks
    private ExaminerServiceImpl examinerService;  // Подключаем наш сервис

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Метод GetQuestion(int amount) - стандартный запрос")
    void testGetQuestions() {
        when(questionService.getAll()).thenReturn(List.of(new Question("Тест", "Ответ")));

        Collection<Question> result = examinerService.getQuestions(1);

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Метод GetQuestion(int amount) - запрошено 2 вопроса, в БД 1 - QuestionOverloadException")
    void testGetQuestionsWhenOverload() {
        when(questionService.getAll()).thenReturn(List.of(new Question("Тест", "Ответ")));

        assertThrows(QuestionOverloadException.class, () -> examinerService.getQuestions(2));
    }

    @Test
    @DisplayName("Метод GetQuestion(int amount) - запрошено 1 вопрос из пустой базы - QuestionOverloadException")
    void testGetQuestionsWhenEmpty() {
        when(questionService.getAll()).thenReturn(Collections.emptyList());

        assertThrows(QuestionOverloadException.class, () -> examinerService.getQuestions(1));
    }

    @Test
    @DisplayName("Метод GetQuestion(int amount) - запрошено 0 вопросов - QuestionBadRequestException")
    void testGetQuestionsZeroAmount() {
        when(questionService.getAll()).thenReturn(Collections.emptyList());

        assertThrows(QuestionBadRequestException.class, () -> {
            examinerService.getQuestions(0);
        });
    }
}
