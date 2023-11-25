package com.moscow.cup.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class RoomEditRequest extends RoomCreateRequest {
    private UUID roomId;
}
