package com.purpleprint.network.purpleprintproject.common.exception;

import com.purpleprint.network.purpleprintproject.auth.command.application.exception.ChildAccountCreationFailException;
import com.purpleprint.network.purpleprintproject.auth.command.application.exception.ConnectFailException;
import com.purpleprint.network.purpleprintproject.auth.command.application.exception.DeleteUserFailException;
import com.purpleprint.network.purpleprintproject.auth.command.application.exception.GrantFailException;
import com.purpleprint.network.purpleprintproject.auth.command.application.exception.LoginFailedException;
import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Class : ExceptionHandler
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@ControllerAdvice // @Controller나 @RestController 에서 발생한 예외를 한 곳에서 관리하고 처리할 수 있게 도와주는 어노테이션
public class CommonExceptionHandler {

    @ExceptionHandler(value = {LoginFailedException.class})
    protected ResponseEntity<?> loginFailedException(LoginFailedException ex) {

        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("error", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), responseMap));
    }

    @ExceptionHandler(value = {ChildAccountCreationFailException.class})
    protected ResponseEntity<?> childAccountCreationFailedException(ChildAccountCreationFailException ex) {

        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("error", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), responseMap));
    }

    @ExceptionHandler(value = {DeleteUserFailException.class})
    protected ResponseEntity<?> deleteUserFailedException(DeleteUserFailException ex) {

        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("error", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), responseMap));
    }


    @ExceptionHandler(value = {GrantFailException.class})
    protected ResponseEntity<?> grantFailExceptionHandler(GrantFailException ex) {
        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("error", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), responseMap));
    }

    @ExceptionHandler(value = {ConnectFailException.class})
    protected ResponseEntity<?> connectChildFailedException(ConnectFailException ex) {


        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("error", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), responseMap));
    }
}
