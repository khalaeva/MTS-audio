package com.challenger.eurekaclientlobby.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.challenger.eurekaclientlobby.dto.request.RoomCreateRequest;
import com.challenger.eurekaclientlobby.dto.request.RoomEditRequest;
import com.challenger.eurekaclientlobby.dto.response.RoomCreateResponse;
import com.challenger.eurekaclientlobby.dto.socket.SocketDto;
import com.challenger.eurekaclientlobby.dto.socket.SocketEvent;
import com.challenger.eurekaclientlobby.entity.RoomEntity;
import com.challenger.eurekaclientlobby.entity.UserEntity;
import com.challenger.eurekaclientlobby.exception.*;
import com.challenger.eurekaclientlobby.repository.MemberRepository;
import com.challenger.eurekaclientlobby.repository.RoomRepository;
import com.challenger.eurekaclientlobby.repository.ServerRepository;
import com.challenger.eurekaclientlobby.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final SocketService socketService;

    private final String URL_SERVER = "/server";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UUID mock = UUID.fromString("593d0d4a-8b19-11ee-b9d1-0242ac120002");

    @Override
    @SneakyThrows
    public RoomCreateResponse createRoom(String cookie, RoomCreateRequest request) {
        // Из куки там анау мынау берем айди
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

        if (isNotInRoomOrLeave(userId)){
            throw new FailedDisconnectRoomException();
        }

        UUID serverId = roomRepository.getServerId(roomId).orElseThrow(RoomIsNotFoundException::new);
        if (memberRepository.existsByUserIdAndServerId(userId, serverId)){
            throw new AccessDeniedException();
        }

        RoomEntity roomEntity = roomRepository.findById(roomId)
                .orElseThrow(RoomIsNotFoundException::new);

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(UserIsNotFoundException::new);

        roomEntity.getListeners().add(userEntity);
        roomRepository.save(roomEntity);

        String message = objectMapper.writeValueAsString(
                SocketDto.builder()
                        .event(SocketEvent.JOINED_ROOM)
                        .roomId(roomEntity.getId())
                        .serverId(roomEntity.getServerId())
                        .username(userEntity.getUsername())
                        .build()
        );
        socketService.sendMessage(serverId.toString(), URL_SERVER, message);
    }

    @Override
    public void leaveRoom(String cookie) {
        UUID userId = mock;
        if (isNotInRoomOrLeave(userId)) {
            throw new UserNotInRoomException();
        }
    }

    @SneakyThrows
    private boolean isNotInRoomOrLeave(UUID userId){

        UserEntity userEntity = userRepository.findByIdAndCurrentRoomIsNotNull(userId)
                .orElse(null);

        if (userEntity == null){
            return false;
        }

        if(!userEntity.getCurrentRoom().getListeners().remove(userEntity)){
            return true;
        }

        roomRepository.save(userEntity.getCurrentRoom());
        String message = objectMapper.writeValueAsString(
                SocketDto.builder()
                        .event(SocketEvent.LEAVED_ROOM)
                        .roomId(userEntity.getCurrentRoom().getId())
                        .serverId(userEntity.getCurrentRoom().getServerId())
                        .userId(userId)
                        .build()
        );
        socketService.sendMessage(userEntity.getCurrentRoom().getServerId().toString(), URL_SERVER, message);
        return false;
    }


}
