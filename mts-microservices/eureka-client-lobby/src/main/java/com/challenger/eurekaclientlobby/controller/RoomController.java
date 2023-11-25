package com.challenger.eurekaclientlobby.controller;

import com.challenger.eurekaclientlobby.dto.request.RoomCreateRequest;
import com.challenger.eurekaclientlobby.dto.request.RoomEditRequest;
import com.challenger.eurekaclientlobby.dto.response.RoomCreateResponse;
import com.challenger.eurekaclientlobby.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/server/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomCreateResponse createRoom(
            @RequestHeader("Cookie") String cookie,
            @RequestBody RoomCreateRequest request
    ) {
        return roomService.createRoom(cookie, request);
    }

    @PutMapping("/edit")
    public void editRoom(
            @RequestHeader("Cookie") String cookie,
            @RequestBody RoomEditRequest request
    ) {
        roomService.editRoom(cookie, request);
    }

    @PostMapping("/join/{id}")
    public void joinInRoom(
            @RequestHeader("Cookie") String cookie,
            @PathVariable UUID id
    ) {
        roomService.joinRoom(cookie, id);
    }

    @PostMapping("/leave")
    public void joinInRoom(@RequestHeader("Cookie") String cookie) {
        roomService.leaveRoom(cookie);
    }

}
