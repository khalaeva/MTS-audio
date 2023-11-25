package com.challenger.eurekaclientlobby.service;

import com.moscow.cup.dto.request.ServerCreateRequest;
import com.moscow.cup.dto.response.ServerCreateResponse;

public interface ServerService {
    ServerCreateResponse createServer(String cookie, ServerCreateRequest request);
}
