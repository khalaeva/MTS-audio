package com.challenger.eurekastreaming.service;

import com.challenger.eurekastreaming.dto.StreamBytesDto;
import org.springframework.http.HttpRange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface StreamService {
    ResponseEntity<StreamBytesDto> getStreamBytes(String file, HttpRange range);
    ResponseEntity<StreamingResponseBody> getStreamingResponse(String httpRange, String filePath);
}
