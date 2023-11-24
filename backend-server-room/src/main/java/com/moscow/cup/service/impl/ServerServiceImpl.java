package com.moscow.cup.service.impl;

import com.moscow.cup.dto.request.ServerCreateRequest;
import com.moscow.cup.dto.response.ServerCreateResponse;
import com.moscow.cup.entity.ServerEntity;
import com.moscow.cup.repository.ServerRepository;
import com.moscow.cup.service.ServerService;
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
