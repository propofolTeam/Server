package com.hackerton.propofol.exception;

public class UserDuplicateException extends BusinessException {

    public UserDuplicateException() {
        super(ErrorCode.USER_DUPLICATION);
    }
}
