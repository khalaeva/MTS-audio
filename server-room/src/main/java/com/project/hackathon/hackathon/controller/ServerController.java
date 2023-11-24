package com.project.hackathon.hackathon.controller;

import com.project.hackathon.hackathon.dto.request.ServerCreateRequest;
import com.project.hackathon.hackathon.dto.response.ServerCreateResponse;
import com.project.hackathon.hackathon.service.ServerService;
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
