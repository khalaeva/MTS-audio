package com.project.hackathon.service;

import com.project.hackathon.dto.request.ServerCreateRequest;
import com.project.hackathon.dto.response.ServerCreateResponse;

public interface ServerService {
    ServerCreateResponse createServer(String cookie, ServerCreateRequest request);
}
