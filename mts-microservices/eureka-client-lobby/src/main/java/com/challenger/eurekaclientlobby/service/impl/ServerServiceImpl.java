package com.challenger.eurekaclientlobby.service.impl;

import com.challenger.eurekaclientlobby.dto.request.ServerCreateRequest;
import com.challenger.eurekaclientlobby.dto.response.ServerCreateResponse;
import com.challenger.eurekaclientlobby.entity.ServerEntity;
import com.challenger.eurekaclientlobby.repository.ServerRepository;
import com.challenger.eurekaclientlobby.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    private final UUID mock = UUID.fromString("593d0d4a-8b19-11ee-b9d1-0242ac120002");

    @Override
    public ServerCreateResponse createServer(
            String cookie,
            ServerCreateRequest request
    ) {

        UUID userId = mock;
        return mapper(
                serverRepository.save(
                        ServerEntity.builder()
                                .ownerUser(userId)
                                .name(request.getName())
                                .build()
                )
        );
    }

    private ServerCreateResponse mapper(ServerEntity entity){
        return ServerCreateResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
