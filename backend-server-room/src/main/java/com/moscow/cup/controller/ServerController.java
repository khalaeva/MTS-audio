package com.moscow.cup.controller;

import com.moscow.cup.dto.request.ServerCreateRequest;
import com.moscow.cup.dto.response.ServerCreateResponse;
import com.moscow.cup.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/server")
@RequiredArgsConstructor
public class ServerController {

    private final ServerService serverService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ServerCreateResponse createLobby(
            @RequestHeader("Cookie") String cookie,
            @RequestBody ServerCreateRequest request
    ) {
        return serverService.createServer(cookie, request);
    }

}
