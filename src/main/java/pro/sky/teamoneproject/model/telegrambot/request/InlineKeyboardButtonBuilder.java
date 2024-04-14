package pro.sky.teamoneproject.model.telegrambot.request;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;

import java.util.Objects;

public abstract class InlineKeyboardButtonBuilder {
    private String text;
    private String callbackData;

    public InlineKeyboardButtonBuilder(String text) {
        this.text = text;
        callbackData = getClass().getSimpleName().replaceAll("[iI]nline[bB]utton[bB]uilder", "");
    }

    public String getText() {
        return text;
    }

    public String getCallbackData() {
        return callbackData.toLowerCase();
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }

    public InlineKeyboardButton buildInlineKeyboardButton() {
        return new InlineKeyboardButton(text).callbackData(callbackData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InlineKeyboardButtonBuilder that)) return false;

        if (!Objects.equals(text, that.text)) return false;
        return Objects.equals(callbackData, that.callbackData);
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (callbackData != null ? callbackData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InlineKeyboardCallbackButton{" +
                "text='" + text + '\'' +
                ", callbackData='" + callbackData + '\'' +
                '}';
    }

    /**
     * Действие выполняемое при нажатии кнопки
     * @param update полученное обновления (сообщения, callback)
     */
    public abstract void onClick(Update update);
}
