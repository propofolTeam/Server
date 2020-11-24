package com.hackerton.propofol.exception;

public class PostNotFoundException extends BusinessException{

    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
