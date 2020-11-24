package com.hackerton.propofol.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    BAD_REQUEST(400, "Bad Request(Invalid Parameter)"),
    INVALID_AUTH_EMAIL(400,"Invalid Auth Email"),
    INVALID_AUTH_CODE(400,"Invalid Auth Code"),

    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),
    UNAUTHORIZED(401, "Authentication is required and has failed or has not yet been provided."),

    USER_NOT_FOUND(404, "User Not Found"),
    POST_NOT_FOUND(404, "Post Not Found"),

    PASSWORD_SAME(409, "Password Same Before"),
    USER_DUPLICATION(409, "User is Already Exists"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;

}