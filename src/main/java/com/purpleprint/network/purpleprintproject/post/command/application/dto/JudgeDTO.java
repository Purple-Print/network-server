package com.purpleprint.network.purpleprintproject.post.command.application.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <pre>
 * Class : JudgeDTO
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgeDTO {

    @NotNull(message = "게시 신청 아이디는 필수 입력 값입니다.")
    private int postId;
    @NotBlank(message = "게시 승인 여부는 필수 입력 값입니다.")
    private String approval;
    private String companionReason;
}
