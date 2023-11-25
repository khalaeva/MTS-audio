package com.challenger.eurekaclientlobby.service.impl;

import com.challenger.eurekaclientlobby.dto.request.ServerCreateRequest;
import com.challenger.eurekaclientlobby.dto.response.ServerCreateResponse;
import com.challenger.eurekaclientlobby.entity.ServerEntity;
import com.challenger.eurekaclientlobby.repository.ServerRepository;
import com.challenger.eurekaclientlobby.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public ServerCreateResponse createServer(
            String cookie,
            ServerCreateRequest request
    ) {
        return mapper(
                serverRepository.save(
                        ServerEntity.builder()
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
