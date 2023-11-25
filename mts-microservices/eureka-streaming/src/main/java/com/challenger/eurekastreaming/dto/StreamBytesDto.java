package com.challenger.eurekastreaming.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Getter
@Setter
@Builder
public class StreamBytesDto {
    private StreamingResponseBody streamingResponseBody;
    private long fileSize;
    private long rangeStart;
    private long rangeEnd;
}
