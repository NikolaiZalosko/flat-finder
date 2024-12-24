package com.nickz.flatfinder.service.impl;

import com.nickz.flatfinder.service.TelegramService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class TelegramServiceImpl implements TelegramService {

    @Value("${telegram.chatId}")
    private String chatId;

    private final TelegramClient telegramClient ;

    public TelegramServiceImpl(@Value("${telegram.bot.token}") String botToken) {
        this.telegramClient = new OkHttpTelegramClient(botToken);
    }

    public void sendMessage(String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
