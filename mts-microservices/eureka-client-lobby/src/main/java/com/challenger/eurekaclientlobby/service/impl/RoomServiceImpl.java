package com.challenger.eurekaclientlobby.service.impl;

import com.challenger.eurekaclientlobby.entity.*;
import com.challenger.eurekaclientlobby.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.challenger.eurekaclientlobby.dto.request.RoomCreateRequest;
import com.challenger.eurekaclientlobby.dto.request.RoomEditRequest;
import com.challenger.eurekaclientlobby.dto.response.RoomCreateResponse;
import com.challenger.eurekaclientlobby.dto.socket.SocketDto;
import com.challenger.eurekaclientlobby.dto.socket.SocketEvent;
import com.challenger.eurekaclientlobby.exception.*;
import com.challenger.eurekaclientlobby.service.RoomService;
import com.challenger.eurekaclientlobby.service.SocketService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {


    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    private final ServerRepository serverRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final SocketService socketService;

    private final String URL_SERVER = "/server";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UUID mock = UUID.fromString("593d0d4a-8b19-11ee-b9d1-0242ac120002");

    @Override
    @SneakyThrows
    public RoomCreateResponse createRoom(String cookie, RoomCreateRequest request) {

        UUID userId = mock;

        if (!serverRepository.existsByIdAndOwnerUser(request.getServerId(), userId)){
            throw new AccessDeniedException();
        }

        RoomEntity roomEntity = roomRepository.save(
                RoomEntity.builder()
                        .name(request.getName())
                        .serverId(request.getServerId())
                .build());

        String message = objectMapper.writeValueAsString(
                SocketDto.builder()
                        .event(SocketEvent.CREATED_ROOM)
                        .roomId(roomEntity.getId())
                        .serverId(roomEntity.getServerId())
                        .name(roomEntity.getName())
                        .build()
        );

        socketService.sendMessage(request.getServerId().toString(), URL_SERVER, message);
        return RoomCreateResponse.builder()
                .roomId(roomEntity.getId())
                .serverId(roomEntity.getServerId())
                .build();
    }

    @SneakyThrows
    @Override
    public void editRoom(String cookie, RoomEditRequest request) {
        UUID userId = mock;

        if (!serverRepository.existsByIdAndOwnerUser(request.getServerId(), userId)){
            throw new AccessDeniedException();
        }

        RoomEntity roomEntity = roomRepository.findById(request.getRoomId())
                .orElseThrow(RoomIsNotFoundException::new);

        roomEntity.setName(request.getName());
        roomRepository.save(roomEntity);

        String message = objectMapper.writeValueAsString(
                SocketDto.builder()
                        .event(SocketEvent.EDIT_ROOM)
                        .roomId(roomEntity.getId())
                        .serverId(roomEntity.getServerId())
                        .name(roomEntity.getName())
                        .build()
        );
        socketService.sendMessage(request.getServerId().toString(), URL_SERVER, message);
    }

    @SneakyThrows
    @Override
    public void joinRoom(String cookie, UUID roomId) {

        UUID userId = mock;
        String username = "mock";

        leaveFromRoom(userId);

        UUID serverId = roomRepository.getServerId(roomId)
                .orElseThrow(RoomIsNotFoundException::new);

        if (!memberRepository.existsByUserIdAndServerId(userId, serverId)){
            throw new AccessDeniedException();
        }

        RoomMemberEntity entity = roomMemberRepository.save(RoomMemberEntity.builder()
                        .roomId(roomId)
                        .serverId(serverId)
                        .userId(userId)
                        .status(
                                roomMemberRepository.existsByServerIdAndRoomId(serverId, roomId) ?
                                        RoomMemberStatus.LISTENER : RoomMemberStatus.LEADER
                        )
                .build());

        String message = objectMapper.writeValueAsString(
                SocketDto.builder()
                        .event(SocketEvent.JOINED_ROOM)
                        .roomId(entity.getRoomId())
                        .serverId(entity.getServerId())
                        .username(username)
                        .statusUser(entity.getStatus())
                        .build()
        );
        socketService.sendMessage(serverId.toString(), entity.getRoomId().toString(), message);
    }

    @Override
    public void leaveRoom(String cookie) {
        UUID userId = mock;

        leaveFromRoom(userId);
    }

    @SneakyThrows
    private void leaveFromRoom(UUID userId) {

        RoomMemberEntity roomMemberEntity = roomMemberRepository.findByUserId(userId)
                .orElse(null);

        if (roomMemberEntity == null){
            return;
        }

        roomMemberRepository.delete(roomMemberEntity);

        RoomMemberEntity randomMember = roomMemberRepository.getRandomUser(
                roomMemberEntity.getServerId(),
                roomMemberEntity.getRoomId()
        ).orElse(null);

        if (randomMember != null){
            randomMember.setStatus(RoomMemberStatus.LEADER);
            roomMemberRepository.save(randomMember);
        }

        String message = objectMapper.writeValueAsString(
                SocketDto.builder()
                        .event(SocketEvent.LEAVED_ROOM)
                        .roomId(roomMemberEntity.getRoomId())
                        .serverId(roomMemberEntity.getServerId())
                        .userId(userId)
                        .leaderId(randomMember != null ? randomMember.getUserId() : null)
                        .build()
        );
        socketService.sendMessage(roomMemberEntity.getServerId().toString(), URL_SERVER, message);
    }


}
