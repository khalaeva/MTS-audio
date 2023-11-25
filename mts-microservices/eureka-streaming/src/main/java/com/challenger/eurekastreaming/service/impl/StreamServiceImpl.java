package com.challenger.eurekastreaming.service.impl;

import com.challenger.eurekastreaming.dto.StreamBytesDto;
import com.challenger.eurekastreaming.exception.FileNotFoundException;
import com.challenger.eurekastreaming.service.StreamService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRange;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class StreamServiceImpl implements StreamService {

    @Value("${application.resource}")
    private String folder;

    @Value("${application.content.type}")
    private String contentType;

    @Override
    public ResponseEntity<StreamingResponseBody> getStreamingResponse(String httpRange, String filePath) {

        List<HttpRange> httpRanges = HttpRange.parseRanges(httpRange);
        HttpRange range = httpRanges.stream()
                .findFirst()
                .orElse(null);

        StreamBytesDto streamBytesDto = getStreamBytesDto(filePath, range);

        long byteLength = streamBytesDto.getRangeEnd() - streamBytesDto.getRangeStart() + 1;
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(200)
                .header("Content-Type", contentType)
                .header("Content-Length", Long.toString(byteLength))
                .header("Accept-Ranges", "bytes");

        if (!httpRanges.isEmpty()){
            bodyBuilder.header("Content-Range", "bytes " + streamBytesDto.getRangeStart() +
                    "-" + streamBytesDto.getRangeEnd() +
                    "/" + streamBytesDto.getFileSize());
        }

        return bodyBuilder.body(streamBytesDto.getStreamingResponseBody());
    }

    @SneakyThrows
    private StreamBytesDto getStreamBytesDto(String filePath, HttpRange range) {

        File file = new File(folder + filePath);
        Path path = Path.of(file.getAbsolutePath());
        if (!Files.exists(path)){
            throw new FileNotFoundException();
        }

        long fileSize = Files.size(path);
        long chunkSize = fileSize / 10;

        if (range == null){
            return StreamBytesDto.builder()
                    .streamingResponseBody(outputStream -> {
                        try (InputStream inputStream = Files.newInputStream(path)){
                            inputStream.transferTo(outputStream);
                        }
                    })
                    .fileSize(fileSize)
                    .rangeStart(0)
                    .rangeEnd(fileSize)
                    .build();
        }

        long rangeStart = range.getRangeStart(0);
        long rangeEnd = rangeStart + chunkSize;

        if (rangeEnd >= fileSize){
            rangeEnd = fileSize - 1;
        }

        int nBytes = (int) ( (rangeEnd - rangeStart) + 1 );
        return StreamBytesDto.builder()
                .streamingResponseBody(outputStream -> {
                    try (InputStream inputStream = Files.newInputStream(path)) {
                        inputStream.skipNBytes(rangeStart);
                        byte[] bytes = inputStream.readNBytes(nBytes);
                        outputStream.write(bytes);
                    }
                })
                .fileSize(fileSize)
                .rangeStart(rangeStart)
                .rangeEnd(rangeEnd)
                .build();


    }

}
