package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.*;

public class TelegramFront extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new TelegramFront());
        //SetMyCommands
    }

    @Override
    public String getBotUsername() {
        return "Currency_group4_Bot";
    }

    @Override
    public String getBotToken() {
        return "6939606814:AAHurPGRFOC36BlmekpJw31vujhlseh3pEI";
    }


    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);

        if (update.hasMessage() && update.getMessage().getText().equals("/start")){

            SendMessage message = createMessage("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют");
            message.setChatId(chatId);

            attachButtons(message, startButtons());

            sendApiMethodAsync(message);
        }

        if (update.hasCallbackQuery()){
            if (update.getCallbackQuery().getData().equals("get_info")){

                SendMessage message = createMessage("При натисканні на кнопку \"Отримати інфо\"\" користувач отримує актуальний курс відповідно до його налаштувань (округлення, банк і т.д.)");
                message.setChatId(chatId);

                attachButtons(message, getInfoButtons());

                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("settings")){

                SendMessage message = createMessage("Налаштування");
                message.setChatId(chatId);

                attachButtons(message, settingsButtons());
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("chars_after_coma")){

                SendMessage message = createMessage("Виберіть кількість знаків після коми");
                message.setChatId(chatId);

                attachButtons(message, charsAfterComaButtons());
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("bank")){

                SendMessage message = createMessage("Виберіть банк");
                message.setChatId(chatId);

                attachButtons(message, bankButtons());
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("values")){

                SendMessage message = createMessage("Виберіть валюту");
                message.setChatId(chatId);

                attachButtons(message, valuesButtons());
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("time_of_notifications")){

                SendMessage message = createMessage("Виберіть час сповіщення");
                message.setChatId(chatId);

                attachButtons(message, timeOfNotificationsButtons());
                sendApiMethodAsync(message);
            }

        }
    }
    private LinkedHashMap<String,String> startButtons(){
        LinkedHashMap<String,String> startButtons = new LinkedHashMap<String,String>();

        startButtons.put("Отримати інфо", "get_info");
        startButtons.put("Налаштування", "settings");

        return startButtons;
    }
    private LinkedHashMap<String,String> getInfoButtons(){
        LinkedHashMap<String,String> getInfoButtons = new LinkedHashMap<String,String>();

        getInfoButtons.put("Отримати інфо", "get_info");
        getInfoButtons.put("Налаштування", "settings");

        return getInfoButtons;
    }

    private LinkedHashMap<String,String> settingsButtons(){
        LinkedHashMap<String,String> settingsButtons = new LinkedHashMap<String,String>();

        settingsButtons.put("Кількість знаків після коми", "chars_after_coma");
        settingsButtons.put("Банк", "bank");
        settingsButtons.put("Валюти", "values");
        settingsButtons.put("Час сповіщень", "time_of_notifications");

        return settingsButtons;
    }
    private LinkedHashMap<String,String> charsAfterComaButtons(){
        LinkedHashMap<String,String> charsAfterComaButtons = new LinkedHashMap<String,String>();

        charsAfterComaButtons.put("2", "chars_after_coma_2");
        charsAfterComaButtons.put("3", "chars_after_coma_3");
        charsAfterComaButtons.put("4", "chars_after_coma_4");

        return charsAfterComaButtons;
    }

    private LinkedHashMap<String,String> valuesButtons() {
        LinkedHashMap<String,String> valuesButtons = new LinkedHashMap<String,String>();

        valuesButtons.put("USD", "values_usd");
        valuesButtons.put("EUR", "values_eur");

        return valuesButtons;
    }

    private  LinkedHashMap<String,String> bankButtons() {
        LinkedHashMap<String,String> bankButtons = new LinkedHashMap<String,String>();

        bankButtons.put("НБУ", "bank_nbu");
        bankButtons.put("ПриватБанк", "bank_privat");
        bankButtons.put("Монобанк", "bank_mono");

        return bankButtons;
    }

    private LinkedHashMap<String,String> timeOfNotificationsButtons(){
        LinkedHashMap<String,String> timeOfNotificationsButtons = new LinkedHashMap<String,String>();

        timeOfNotificationsButtons.put("9", "time_of_notifications_9");
        timeOfNotificationsButtons.put("10", "time_of_notifications_10");
        timeOfNotificationsButtons.put("11", "time_of_notifications_11");
        timeOfNotificationsButtons.put("12", "time_of_notifications_12");
        timeOfNotificationsButtons.put("13", "time_of_notifications_13");
        timeOfNotificationsButtons.put("14", "time_of_notifications_14");
        timeOfNotificationsButtons.put("15", "time_of_notifications_15");
        timeOfNotificationsButtons.put("16", "time_of_notifications_16");
        timeOfNotificationsButtons.put("17", "time_of_notifications_17");
        timeOfNotificationsButtons.put("18", "time_of_notifications_18");
        timeOfNotificationsButtons.put("Вимкнути сповіщення", "time_of_notifications_off");

        return timeOfNotificationsButtons;
    }

    private Long getChatId(Update update){
        if (update.hasMessage()){
            return update.getMessage().getFrom().getId();
        }

        if(update.hasCallbackQuery()){
            return update.getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    private SendMessage createMessage(String text){
        SendMessage message = new SendMessage();
        //message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setText(text);
        message.setParseMode("markdown");
        return message;
    }
    private void attachButtons(SendMessage message,LinkedHashMap<String,String> buttons){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (String buttonName : buttons.keySet()){
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            //button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setText(buttonName);
            button.setCallbackData(buttonValue);

            keyboard.add(Arrays.asList(button));
        }
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }
}