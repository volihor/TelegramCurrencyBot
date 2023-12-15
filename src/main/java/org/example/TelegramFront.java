package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.*;
import java.util.regex.Pattern;

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
//        update.getCallbackQuery().getMessage().getMessageId();
//        update.getCallbackQuery().getMessage().getChatId();


        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {

            SendMessage message = createMessage("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют");
            message.setChatId(chatId);

            attachButtons(message, startButtons());

            sendApiMethodAsync(message);
        }

        if (update.hasCallbackQuery()) {
            System.out.println(update.getCallbackQuery().getMessage().getMessageId());
            if (update.getCallbackQuery().getData().matches("chars_after_coma_\\d")) {
                String data = update.getCallbackQuery().getData();
                System.out.println(data.substring(data.lastIndexOf('_') + 1));
                SendMessage message = createMessage("ok1");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().matches("values_\\w+")) {
                SendMessage message = createMessage("ok2");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().matches("time_of_notifications_\\w+")) {
                SendMessage message = createMessage("ok3");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().matches("bank_\\w+")) {
                SendMessage message = createMessage("ok4");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("get_info")) {

                SendMessage message = createMessage("При натисканні на кнопку \"Отримати інфо\" користувач отримує актуальний курс відповідно до його налаштувань (округлення, банк і т.д.)");
                message.setChatId(chatId);

                attachButtons(message, getInfoButtons());

                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("settings")) {

                SendMessage message = createMessage("Налаштування");
                message.setChatId(chatId);

                attachButtons(message, settingsButtons());
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("chars_after_coma")) {

                SendMessage message = createMessage("Виберіть кількість знаків після коми");
                message.setChatId(chatId);

                attachButtons(message, charsAfterComaButtons());
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("bank")) {

                SendMessage message = createMessage("Виберіть банк");
                message.setChatId(chatId);

                attachButtons(message, bankButtons());
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("values")) {

                SendMessage message = createMessage("Виберіть валюту");
                message.setChatId(chatId);

                attachButtons(message, valuesButtons());
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("time_of_notifications")) {

                SendMessage message = createMessage("Виберіть час сповіщення");
                message.setChatId(chatId);

                attachButtons(message, timeOfNotificationsButtons());
                sendApiMethodAsync(message);
            }

        }
    }

    private LinkedHashMap<String, String> startButtons() {
        LinkedHashMap<String, String> startButtons = new LinkedHashMap<String, String>();

        startButtons.put("get_info", "Отримати інфо");
        startButtons.put("settings", "Налаштування");

        return startButtons;
    }

    private LinkedHashMap<String, String> getInfoButtons() {
        LinkedHashMap<String, String> getInfoButtons = new LinkedHashMap<String, String>();

        getInfoButtons.put("get_info", "Отримати інфо");
        getInfoButtons.put("settings", "Налаштування");

        return getInfoButtons;
    }

    private LinkedHashMap<String, String> settingsButtons() {
        LinkedHashMap<String, String> settingsButtons = new LinkedHashMap<String, String>();

        settingsButtons.put("chars_after_coma", "Кількість знаків після коми");
        settingsButtons.put("bank", "Банк");
        settingsButtons.put("values", "Валюти");
        settingsButtons.put("time_of_notifications", "Час сповіщень");

        return settingsButtons;
    }

    private LinkedHashMap<String, String> charsAfterComaButtons() {
        LinkedHashMap<String, String> charsAfterComaButtons = new LinkedHashMap<String, String>();

        charsAfterComaButtons.put("chars_after_coma_2", "2");
        charsAfterComaButtons.put("chars_after_coma_3", "3");
        charsAfterComaButtons.put("chars_after_coma_4", "4");

        return charsAfterComaButtons;
    }

    private LinkedHashMap<String, String> valuesButtons() {
        LinkedHashMap<String, String> valuesButtons = new LinkedHashMap<String, String>();

        valuesButtons.put("values_usd", "USD");
        valuesButtons.put("values_eur", "EUR");

        return valuesButtons;
    }

    private LinkedHashMap<String, String> bankButtons() {
        LinkedHashMap<String, String> bankButtons = new LinkedHashMap<String, String>();

        bankButtons.put("bank_nbu", "НБУ");
        bankButtons.put("bank_privat", "ПриватБанк");
        bankButtons.put("bank_mono", "Монобанк");

        return bankButtons;
    }

    private LinkedHashMap<String, String> timeOfNotificationsButtons() {
        LinkedHashMap<String, String> timeOfNotificationsButtons = new LinkedHashMap<String, String>();

        timeOfNotificationsButtons.put("time_of_notifications_9", "9");
        timeOfNotificationsButtons.put("time_of_notifications_10", "10");
        timeOfNotificationsButtons.put("time_of_notifications_11", "11");
        timeOfNotificationsButtons.put("time_of_notifications_12", "12");
        timeOfNotificationsButtons.put("time_of_notifications_13", "13");
        timeOfNotificationsButtons.put("time_of_notifications_14", "14");
        timeOfNotificationsButtons.put("time_of_notifications_15", "15");
        timeOfNotificationsButtons.put("time_of_notifications_16", "16");
        timeOfNotificationsButtons.put("time_of_notifications_17", "17");
        timeOfNotificationsButtons.put("time_of_notifications_18", "18");
        timeOfNotificationsButtons.put("time_of_notifications_off", "Вимкнути сповіщення");

        return timeOfNotificationsButtons;
    }

    private Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }

        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    private SendMessage createMessage(String text) {
        SendMessage message = new SendMessage();
        //message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setText(text);
        message.setParseMode("markdown");
        return message;
    }

    private void attachButtons(SendMessage message, LinkedHashMap<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (String buttonValue : buttons.keySet()) {
            String buttonName = buttons.get(buttonValue);

            InlineKeyboardButton button = new InlineKeyboardButton();
            //button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setText(buttonName);
            button.setCallbackData(buttonValue);

            keyboard.add(Arrays.asList(button));
        }
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    private void editButtons() {

    }
}