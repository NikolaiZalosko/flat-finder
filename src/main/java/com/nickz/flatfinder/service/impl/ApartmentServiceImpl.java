package com.nickz.flatfinder.service.impl;

import com.nickz.flatfinder.entity.Apartment;
import com.nickz.flatfinder.feign.KufarFeignClient;
import com.nickz.flatfinder.feign.OnlinerFeignClient;
import com.nickz.flatfinder.mapper.ApartmentMapper;
import com.nickz.flatfinder.repository.ApartmentRepository;
import com.nickz.flatfinder.service.ApartmentService;
import com.nickz.flatfinder.service.TelegramService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final OnlinerFeignClient onlinerFeignClient;
    private final KufarFeignClient kufarFeignClient;
    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;
    private final TelegramService telegramService;

    @Transactional
    public void notifyAboutNewApartments() {
        List<Apartment> apartmentFoundOnline = new ArrayList<>();

        List<Apartment> apartmentsOnliner =
                onlinerFeignClient.searchApartments().apartments().stream()
                        .map(apartmentMapper::toEntity)
                        .toList();
        log.info("Apartments found at Onliner: {}", apartmentsOnliner);

        List<Apartment> apartmentsKufar =
                kufarFeignClient.searchApartments().ads().stream()
                        .map(apartmentMapper::toEntity)
                        .toList();
        log.info("Apartments found at Kufar: {}", apartmentsOnliner);

        apartmentFoundOnline.addAll(apartmentsOnliner);
        apartmentFoundOnline.addAll(apartmentsKufar);

        List<Apartment> apartmentsDb = apartmentRepository.findAll();
        log.info("Apartments fetched from DB: {}", apartmentsDb);

        List<Apartment> newApartments = newItems(apartmentsDb, apartmentFoundOnline);
        log.info("New apartments: {}", newApartments);

        apartmentRepository.deleteAll();
        apartmentRepository.saveAll(apartmentFoundOnline);

        if (!newApartments.isEmpty()) {
            sendTelegramNotificationAboutNewApartments(newApartments);
        }
    }

    private void sendTelegramNotificationAboutNewApartments(List<Apartment> newApartments) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Найдены новые объявления:\n");
        newApartments.forEach(apartment -> {
            messageBuilder.append("\n");
            messageBuilder.append(apartment.getUrl());
        });
        telegramService.sendMessage(messageBuilder.toString());
    }

    private <T> List<T> newItems(List<T> oldList, List<T> currentList) {
        List<T> newItems = new ArrayList<>(currentList);
        newItems.removeAll(oldList);
        return newItems;
    }
}
