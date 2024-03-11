package pro.sky.teamoneproject.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.*;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.teamoneproject.commands.Command;
import pro.sky.teamoneproject.commands.ShelterDefaultCommand;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import pro.sky.teamoneproject.commands.buttonsforpets.InfoAboutOfPetDefaultCommand;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@ExtendWith(MockitoExtension.class)
public class TelegramBotListenerTest {

    Logger loggerMock = mock(Logger.class);
    Update updateMock = mock(Update.class);
    Message messageMock = mock(Message.class);
    CallbackQuery callbackQueryMock = mock(CallbackQuery.class);
    Chat chatMock = mock(Chat.class);
    User userMock = mock(User.class);
    TelegramBot telegramBotMock = mock(TelegramBot.class);
    TelegramBotListener telegramBotListenerMock = mock(TelegramBotListener.class);

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private Command defaultShelterCommand;

    @Mock
    private Command defaultPetCommand;

    @Test
    void processTest() {
        List<Update> updates = List.of(updateMock, updateMock, updateMock, updateMock, updateMock);
        when(messageMock.chat()).thenReturn(chatMock);
        when(messageMock.from()).thenReturn(userMock);
        when(callbackQueryMock.from()).thenReturn(userMock);
        when(chatMock.id()).thenReturn(1L);
        when(userMock.isBot()).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(true);
        when(updateMock.message()).thenReturn(messageMock).thenReturn(null).thenReturn(messageMock).thenReturn(null).thenReturn(messageMock);
        when(updateMock.callbackQuery()).thenReturn(null).thenReturn(callbackQueryMock).thenReturn(callbackQueryMock);
        //when(telegramBotServiceMock.processMessage(any())).thenReturn(new BaseResponse()).thenReturn(any(BaseResponse.class));
        //ReflectionTestUtils.setField(out, "logger", loggerMock);
        //out.process(updates);
        //verify(telegramBotListenerMock, new Times(2)).processMessage(any());
       // verify(telegramBotListenerMock, new Times(1)).processCallBackQuery(any());
        //verify(loggerMock, new Times(1)).error(any());
    }
    /*

    @Mock
    private Update updateWithMessage;

    @Mock
    private TelegramBotListener telegramBotListenerMock;

    @Mock
    private Update updateWithCallbackQuery;

    @Test
    public void testProcessShouldProcessMessageUpdate() {
        TelegramBotListener telegramBotListener = new TelegramBotListener();

        // Создаем список обновлений
        List<Update> updates = new ArrayList<>();
        updates.add(updateWithMessage);

        // Устанавливаем поведение для updateWithMessage
        when(updateWithMessage.message()).thenReturn(new Message());

        // Вызываем метод process
        int result = telegramBotListener.process(updates);

        // Проверяем, что метод processMessage был вызван для updateWithMessage
        verify(telegramBotListenerMock, times(1)).processMessage(updateWithMessage);
    }

    @Test
    public void testProcessShouldProcessCallbackQueryUpdate() {
        TelegramBotListener telegramBotListener = new TelegramBotListener();

        // Создаем список обновлений
        List<Update> updates = new ArrayList<>();
        updates.add(updateWithCallbackQuery);

        // Устанавливаем поведение для updateWithCallbackQuery
        when(updateWithCallbackQuery.callbackQuery()).thenReturn(new CallbackQuery());

        // Вызываем метод process
        int result = telegramBotListener.process(updates);

        // Проверяем, что метод processCallbackQuery был вызван для updateWithCallbackQuery
        verify(telegramBotListenerMock, times(1)).processCallbackQuery(updateWithCallbackQuery);
    }
*/
    @Test
    public void testUpdateSheltersCommand() {
        CommandUpdater commandUpdater = new CommandUpdater(applicationContext);

        // Устанавливаем поведение для defaultShelterCommand
        when(defaultShelterCommand instanceof ShelterDefaultCommand).thenReturn(true);
        when(defaultShelterCommand.getCommand()).thenReturn("shelterCommand");

        // Устанавливаем коллекцию commands с defaultShelterCommand
        Map<String, Command> commands = new HashMap<>();
        commands.put("shelterCommand", defaultShelterCommand);

        // Устанавливаем поведение applicationContext.removeBeanDefinition
        doNothing().when(applicationContext).removeBeanDefinition("shelterCommand");

        // Устанавливаем коллекцию commands метод invoke удаляет команды
        commandUpdater.setCommands(commands);
        commandUpdater.updateSheltersCommand();

        // Проверяем, что removeBeanDefinition вызывается один раз
        verify(applicationContext, times(1)).removeBeanDefinition("shelterCommand");
    }

    @Test
    public void testUpdatePetsCommand() {
        CommandUpdater commandUpdater = new CommandUpdater(applicationContext);

        // Устанавливаем поведение для defaultPetCommand
        when(defaultPetCommand instanceof InfoAboutOfPetDefaultCommand).thenReturn(true);
        when(defaultPetCommand.getCommand()).thenReturn("petCommand");

        // Устанавливаем коллекцию commands с defaultPetCommand
        Map<String, Command> commands = new HashMap<>();
        commands.put("petCommand", defaultPetCommand);

        // Устанавливаем поведение applicationContext.removeBeanDefinition
        doNothing().when(applicationContext).removeBeanDefinition("petCommand");

        // Устанавливаем коллекцию commands метод invoke удаляет команды
        commandUpdater.setCommands(commands);
        commandUpdater.updatePetsCommand();

        // Проверяем, что removeBeanDefinition вызывается один раз
        verify(applicationContext, times(1)).removeBeanDefinition("petCommand");
    }

}