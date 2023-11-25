package com.moscow.cup.service.impl;

import com.moscow.cup.service.SocketService;
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
