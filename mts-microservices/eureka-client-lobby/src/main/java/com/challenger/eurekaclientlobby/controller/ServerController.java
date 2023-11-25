package com.challenger.eurekaclientlobby.controller;

import com.challenger.eurekaclientlobby.dto.request.ServerCreateRequest;
import com.challenger.eurekaclientlobby.dto.response.ServerCreateResponse;
import com.challenger.eurekaclientlobby.service.ServerService;
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
