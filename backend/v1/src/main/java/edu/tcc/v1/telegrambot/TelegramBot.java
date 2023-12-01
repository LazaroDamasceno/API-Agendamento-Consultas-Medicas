package edu.tcc.v1.telegrambot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    TelegramBot() {
        super("6613626161:AAGkeSm-vSY13Ee990ZUtMY9X79Rt7Amh9M");
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sm = new SendMessage();
        sm.setChatId(update.getMessage().getChatId());
        sm.setText(update.getMessage().getText());
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "AvisosConsultasBot";
    }

}