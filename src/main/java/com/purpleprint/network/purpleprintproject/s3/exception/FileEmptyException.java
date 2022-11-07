package com.purpleprint.network.purpleprintproject.s3.exception;

/**
 * <pre>
 * Class : FileEmptyException
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class FileEmptyException extends RuntimeException {
    public FileEmptyException(String message) { super(message); }
}
