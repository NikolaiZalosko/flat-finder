package com.nickz.flatfinder.scheduler;

import com.nickz.flatfinder.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class FlatFinderJob {

    private final ApartmentService apartmentService;

    @Scheduled(fixedRate = 5000)
    public void execute() throws TelegramApiException {
        apartmentService.notifyAboutNewApartments();
    }
}
