package com.challenger.eurekaclientlobby.service.impl;

import com.challenger.eurekaclientlobby.service.SocketService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketServiceImpl implements SocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @SneakyThrows
    @Override
    public void sendMessage(String id, String description, String value) {
        simpMessagingTemplate.convertAndSendToUser(id, description, value);
    }
}
