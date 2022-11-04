package com.purpleprint.network.purpleprintproject.common.dto;

import org.springframework.http.HttpStatus;

/**
 * <pre>
 * Class : ApiExceptionDTO
 * Comment: api 예외처리 DTO
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class ApiExceptionDTO {
    private int state;
    private String message;

    public ApiExceptionDTO(HttpStatus state, String message){
        this.state = state.value();
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiExceptionDto{" +
                "state=" + state +
                ", message='" + message + '\'' +
                '}';
    }
}