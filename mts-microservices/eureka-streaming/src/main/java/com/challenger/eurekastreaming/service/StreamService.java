package com.challenger.eurekastreaming.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface StreamService {
    ResponseEntity<StreamingResponseBody> getStreamingResponse(String httpRange, String filePath);
}
