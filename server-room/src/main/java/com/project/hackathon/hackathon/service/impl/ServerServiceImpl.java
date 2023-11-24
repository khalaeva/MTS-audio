package com.project.hackathon.hackathon.service.impl;

import com.project.hackathon.hackathon.dto.request.ServerCreateRequest;
import com.project.hackathon.hackathon.dto.response.ServerCreateResponse;
import com.project.hackathon.hackathon.entity.ServerEntity;
import com.project.hackathon.hackathon.repository.ServerRepository;
import com.project.hackathon.hackathon.service.ServerService;
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
