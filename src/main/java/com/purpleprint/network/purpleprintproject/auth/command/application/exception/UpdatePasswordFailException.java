package com.purpleprint.network.purpleprintproject.auth.command.application.exception;

/**
 * <pre>
 * Class : UpdatePasswordFailException
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-06       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class UpdatePasswordFailException extends RuntimeException {
    public UpdatePasswordFailException(String message) { super(message); }
}
