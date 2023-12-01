package edu.tcc.v1.telegrambot;

public interface InstanciarTelegramBot {

    static TelegramBot instanciar() {
        return new TelegramBot();
    }

}
