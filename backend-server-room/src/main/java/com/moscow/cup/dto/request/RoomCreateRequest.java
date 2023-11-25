package com.moscow.cup.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoomCreateRequest {
    protected UUID serverId;
    protected String name;
}
