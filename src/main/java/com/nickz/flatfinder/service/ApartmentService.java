package com.nickz.flatfinder.service;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface ApartmentService {

    void notifyAboutNewApartments() throws TelegramApiException;
}
