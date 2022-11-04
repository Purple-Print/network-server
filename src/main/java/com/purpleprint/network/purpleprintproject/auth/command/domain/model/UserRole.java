package com.purpleprint.network.purpleprintproject.auth.command.domain.model;

/**
 * <pre>
 * Class : UserRole
 * Comment: 유저 권한
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-01       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see 
 */
public enum UserRole {
    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private String text;
    UserRole(String text) {this.text = text;}

    public String getText() { return text; }
}
