package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.*;

public class Main extends TelegramLongPollingBot {
    private Map<Long, Integer> levels = new HashMap<>();
    //test comm
    //test comm2
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());
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

            attachButtons(message, Map.of(
                    "Налаштування", "settings",
                    "Отримати інфо", "get_info"
            ));

            sendApiMethodAsync(message);
        }

        if (update.hasCallbackQuery()){
            if (update.getCallbackQuery().getData().equals("get_info")){

                SendMessage message = createMessage("При натисканні на кнопку \"Отримати інфо\"\" користувач отримує актуальний курс відповідно до його налаштувань (округлення, банк і т.д.)");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "Налаштування", "settings",
                        "Отримати інфо", "get_info"
                ));

                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("settings")){

                SendMessage message = createMessage("Налаштування");
                message.setChatId(chatId);

                LinkedHashMap<String,String> settings = new LinkedHashMap<String,String>();
                settings.putAll(Map.of(
                        "Час оповіщень", "time_of_notifications",
                        "Банк", "bank",
                        "Валюти", "values",
                        "Кількість знаків після коми", "chars_after_coma"
                ));

                attachButtons(message, settings);
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("chars_after_coma")){

                SendMessage message = createMessage("Виберіть кількість знаків після коми");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "2", "chars_after_coma_2",
                        "3", "chars_after_coma_3",
                        "4", "chars_after_coma_4"
                ));
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("bank")){

                SendMessage message = createMessage("Виберіть банк");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "НБУ", "bank_nbu",
                        "ПриватБанк", "bank_privat",
                        "Монобанк", "bank_mono"
                ));
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("values")){

                SendMessage message = createMessage("Виберіть валюту");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "USD", "bank_usd",
                        "EUR", "bank_eur"
                ));
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("time_of_notifications")){

                SendMessage message = createMessage("*Вітаємо на останньому рівні! Твій гусак - готова біологічна зброя - бандерогусак.*\n" +
                        "Баланс: 50 монет. \n" +
                        "Тепер ти можеш придбати Джавелін і глушити чмонь");
                message.setChatId(chatId);

                attachButtons(message, Map.of(
                        "Купити Джавелін (50 монет)", "level_4_task"
                ));
                sendApiMethodAsync(message);
            }

        }
    }
    public Long getChatId(Update update){
        if (update.hasMessage()){
            return update.getMessage().getFrom().getId();
        }

        if(update.hasCallbackQuery()){
            return update.getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    public SendMessage createMessage(String text){
        SendMessage message = new SendMessage();
        //message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setText(text);
        message.setParseMode("markdown");
        return message;
    }
    public void attachButtons(SendMessage message,LinkedHashMap<String,String> buttons){
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
//    public void sendImage(String name, Long chatid){
//        SendAnimation animation = new SendAnimation();
//
//        InputFile inputFile = new InputFile();
//        inputFile.setMedia(new File("images/" + name + ".gif"));
//
//        animation.setAnimation(inputFile);
//        animation.setChatId(chatid);
//
//        executeAsync(animation);
//    }


}