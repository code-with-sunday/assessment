package com.ethnocopia.exception;
import org.springframework.http.HttpStatus;

public class EthnocopiaApiResponse extends RuntimeException{
    private HttpStatus status;
    private String message;

    public EthnocopiaApiResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public EthnocopiaApiResponse(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
