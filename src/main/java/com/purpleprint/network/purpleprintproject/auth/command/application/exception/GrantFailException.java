package com.purpleprint.network.purpleprintproject.auth.command.application.exception;

import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Class : GrantFailException
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-06       이상학           최초 생성
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
public class GrantFailException extends RuntimeException{

    public GrantFailException(String message) {
        super(message);
    }

}
