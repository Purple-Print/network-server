package com.purpleprint.network.purpleprintproject.character.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class : ResponseDTO
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       이상학           최초 생성
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    @JsonProperty("face shape")
    private String faceShape;

    @JsonProperty("skin color")
    private String skinColor;
}
