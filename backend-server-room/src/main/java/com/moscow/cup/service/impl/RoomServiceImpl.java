package com.moscow.cup.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moscow.cup.dto.request.RoomCreateRequest;
import com.moscow.cup.dto.request.RoomEditRequest;
import com.moscow.cup.dto.response.RoomCreateResponse;
import com.moscow.cup.dto.socket.SocketDto;
import com.moscow.cup.dto.socket.SocketEvent;
import com.moscow.cup.entity.RoomEntity;
import com.moscow.cup.entity.UserEntity;
import com.moscow.cup.exception.*;
import com.moscow.cup.repository.MemberRepository;
import com.moscow.cup.repository.RoomRepository;
import com.moscow.cup.repository.ServerRepository;
import com.moscow.cup.repository.UserRepository;
import com.moscow.cup.service.RoomService;
import com.moscow.cup.service.SocketService;
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
