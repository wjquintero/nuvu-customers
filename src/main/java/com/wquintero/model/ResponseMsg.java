package com.wquintero.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class ResponseMsg {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public ResponseMsg setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseMsg setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseMsg(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
