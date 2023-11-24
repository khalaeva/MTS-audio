package com.project.hackathon.hackathon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ServerCreateResponse {
    private UUID id;
    private String name;

}
