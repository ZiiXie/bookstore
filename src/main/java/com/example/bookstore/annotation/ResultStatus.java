package com.example.bookstore.annotation;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public enum ResultStatus {
    SUCCESS(HttpStatus.OK.value(), "OK"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Bad Request"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS.value(), "请勿重复请求"),
    USER_NOT_FIND(-99, "请登陆");

    /**
     * 业务状态码
     */
    private Integer code;
    /**
     * 业务信息描述
     */
    private String message;

    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}