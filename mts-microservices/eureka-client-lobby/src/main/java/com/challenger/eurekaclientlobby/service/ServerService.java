package com.challenger.eurekaclientlobby.service;

import com.challenger.eurekaclientlobby.dto.request.ServerCreateRequest;
import com.challenger.eurekaclientlobby.dto.response.ServerCreateResponse;

public interface ServerService {
    ServerCreateResponse createServer(String cookie, ServerCreateRequest request);
}
