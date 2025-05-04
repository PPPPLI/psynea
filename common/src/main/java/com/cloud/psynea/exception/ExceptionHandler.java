package com.cloud.psynea.exception;

import com.cloud.psynea.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(UserExistException.class)
    public ResponseDto<String> userExistException(UserExistException e) {

        return new ResponseDto<>(HttpStatus.BAD_REQUEST,e.getMessage());

    }
}
