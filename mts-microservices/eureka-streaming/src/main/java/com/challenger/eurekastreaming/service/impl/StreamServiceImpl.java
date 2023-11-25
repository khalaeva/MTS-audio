package com.challenger.eurekastreaming.service.impl;

import com.challenger.eurekastreaming.dto.StreamBytesDto;
import com.challenger.eurekastreaming.service.StreamService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpRange;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class StreamServiceImpl implements StreamService {

    private final String DEFAULT_PATH = "resources/";
    private final String DEFAULT_CONTENT_TYPE = "audio/mp4";

    @SneakyThrows
    @Override
    public ResponseEntity<StreamBytesDto> getStreamBytes(String filePath, HttpRange range) {

        File file = new File(DEFAULT_PATH + filePath);
        if (!file.exists()){
            return null;
        }

        long size = Files.size(Path.of(file.getPath()));
        long chunkSize = size / 100;

        if (range == null) {
            ResponseEntity.BodyBuilder builder = ResponseEntity.status(206)
                    .header("Content-Type", DEFAULT_PATH)
                    .header("Accept-Ranges", "bytes")
                    .contentLength(file.length())
            return ResponseEntity.BodyBuilder
        }

        return null;
    }

    @Override
    public ResponseEntity<StreamingResponseBody> getStreamingResponse(String httpRange, String filePath) {

        HttpRange range = HttpRange.parseRanges(httpRange).stream()
                .findFirst()
                .orElse(null);

        StreamBytesDto streamBytesDto = getStreamBytesDto(filePath, range);

        return ;
    }

    private StreamBytesDto getStreamBytesDto(String filePath, HttpRange range){

        File file = new File(DEFAULT_PATH + filePath);
        if (!file.exists()){
            return null;
        }



    }

}
