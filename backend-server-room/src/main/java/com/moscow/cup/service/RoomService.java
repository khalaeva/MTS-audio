package com.moscow.cup.service;

import com.moscow.cup.dto.request.RoomCreateRequest;
import com.moscow.cup.dto.request.RoomEditRequest;
import com.moscow.cup.dto.response.RoomCreateResponse;

import java.util.UUID;

public interface RoomService {
    RoomCreateResponse createRoom(String cookie, RoomCreateRequest request);
    void editRoom(String cookie, RoomEditRequest request);
    void joinRoom(String cookie, UUID roomId);
    void leaveRoom(String cookie);
}
