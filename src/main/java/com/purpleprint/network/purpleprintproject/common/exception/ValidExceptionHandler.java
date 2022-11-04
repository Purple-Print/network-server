package com.purpleprint.network.purpleprintproject.common.exception;

import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Class : ValidExceptionHandler
 * Comment: Valid 예외 처리 핸들러
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
public class ValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("error", errorMessage);

        return ResponseEntity
                .badRequest()
                .body(new ResponseMessage(HttpStatus.BAD_REQUEST, errorMessage, responseMap));
    }
}
