package com.challenger.eurekastreaming.controller;

import com.challenger.eurekastreaming.service.StreamService;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stream")
public class StreamController {

    private final StreamService streamService;

    @GetMapping(value = "/{filePath}")
    public ResponseEntity<StreamingResponseBody> streamAudio(
            @RequestHeader(value = "Range", required = false) String httpRange,
            @PathVariable String filePath
    ) {
        return streamService.getStreamingResponse(httpRange, filePath);
    }

}
