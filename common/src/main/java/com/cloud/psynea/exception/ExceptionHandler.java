package com.cloud.psynea.exception;

import com.cloud.psynea.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(UserExistException.class)
    public ResponseDto<String> userExistException(UserExistException e) {

        return new ResponseDto<>(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ResponseDto<String>  handleBadCredentials(BadCredentialsException ex) {
        return new ResponseDto<>(HttpStatus.UNAUTHORIZED,"Username or password is incorrect");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
    public  ResponseDto<String>  handleGeneral(Exception ex) {
        return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR,"Please try later");
    }
}
