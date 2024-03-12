package pro.sky.teamoneproject.controller;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BaseControllerTest {
    /**В этом тесте мы создаем экземпляр BaseController, а также mock объект для RedirectAttributes.
     * Затем мы вызываем метод get, передавая mock объект, проверяем что flash атрибут был добавлен с правильным значением и что возвращенный
     * RedirectView содержит ожидаемый URL. Такой тест поможет убедиться, что метод get возвращает ожидаемый RedirectView с корректными атрибутами.
     */
    @Test
    void testGet() {
        BaseController baseController = new BaseController();

        // Создаем mock объект для RedirectAttributes
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        // Вызываем метод get
        RedirectView redirectView = baseController.get(redirectAttributes);

        // Проверяем, что flash attribute добавлен
        verify(redirectAttributes, times(1)).addFlashAttribute("flashAttribute", "redirectWithRedirectView");

        // Проверяем, что RedirectView создан с правильным URL
        assertEquals("/swagger-ui/index.html#", redirectView.getUrl());
    }
}


