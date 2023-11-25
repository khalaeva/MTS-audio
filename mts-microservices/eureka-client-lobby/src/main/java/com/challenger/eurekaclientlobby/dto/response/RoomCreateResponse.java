package com.challenger.eurekaclientlobby.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class RoomCreateResponse {
    private UUID roomId;
    private UUID serverId;
}
