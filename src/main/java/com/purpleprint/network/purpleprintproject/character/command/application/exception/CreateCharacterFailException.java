package com.purpleprint.network.purpleprintproject.character.command.application.exception;

/**
 * <pre>
 * Class : CreateCharacterException
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       이상학           최초 생성
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
public class CreateCharacterFailException extends RuntimeException{

    public CreateCharacterFailException(String message) {super(message);}
}
