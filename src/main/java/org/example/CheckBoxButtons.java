//package org.example;
//
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
//
//import java.util.*;
//
//import static java.lang.Math.toIntExact;
//
//public class CheckBoxButtons {
//    public void CheckButtons(Update update0) {
//        String call_data = update0.getCallbackQuery().getData();
//        long message_id = update0.getCallbackQuery().getMessage().getMessageId();
//        long chat_id = update0.getCallbackQuery().getMessage().getChatId();
//        String inline_message_id = update0.getCallbackQuery().getInlineMessageId();
//        if (call_data.equals("change_the_label")) {
//            String answer = "Updated message text";
//            EditMessageReplyMarkup new_message = new EditMessageReplyMarkup();
//            new_message.setChatId(chat_id);
//            new_message.setMessageId(toIntExact(message_id));
//            new_message.setInlineMessageId(inline_message_id);
//            InlineKeyboardButton dk1 = new InlineKeyboardButton();
//            dk1.setText("label1");
//            dk1.setCallbackData("change_the_label");
//            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//            List<InlineKeyboardButton> rowInline = new ArrayList<>();
//
//            rowInline.add(dk1);
//
//            rowsInline.add(rowInline);
//
//            markupInline.setKeyboard(rowsInline);
//            new_message.setReplyMarkup(markupInline);
//
//
//            try {
//                EditMessageReplyMarkup qwer = new EditMessageReplyMarkup();
//                editMessageReplyMarkup(new_message);
//
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}