package com.project.hackathon.hackathon.service;

import com.project.hackathon.hackathon.dto.request.ServerCreateRequest;
import com.project.hackathon.hackathon.dto.response.ServerCreateResponse;

public interface ServerService {
    ServerCreateResponse createServer(String cookie, ServerCreateRequest request);
}
