package com.challenger.eurekaclientlobby.dto.socket;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SocketDto {
    private SocketEvent event;
    private UUID serverId;
    private UUID roomId;
    private String name;
    private UUID userId;
    private String username;
}
