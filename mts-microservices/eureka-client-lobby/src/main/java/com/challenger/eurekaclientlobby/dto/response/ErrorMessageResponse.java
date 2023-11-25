package com.challenger.eurekaclientlobby.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ErrorMessageResponse {
    private String message;
    private Integer code;
    @Builder.Default
    private Date timestamp = new Date();
}
