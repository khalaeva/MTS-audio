package com.challenger.eurekaclientlobby.service;

import com.challenger.eurekaclientlobby.dto.request.RoomCreateRequest;
import com.challenger.eurekaclientlobby.dto.request.RoomEditRequest;
import com.challenger.eurekaclientlobby.dto.response.RoomCreateResponse;

import java.util.UUID;

public interface RoomService {
    RoomCreateResponse createRoom(String cookie, RoomCreateRequest request);
    void editRoom(String cookie, RoomEditRequest request);
    void joinRoom(String cookie, UUID roomId);
    void leaveRoom(String cookie);
}
