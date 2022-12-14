package com.purpleprint.network.purpleprintproject.auth.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class : LogoutV2DTO
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-22       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutV2DTO {
    @JsonProperty("xCoord")
    private Float xCoord;

    @JsonProperty("yCoord")
    private Float yCoord;

    @JsonProperty("zCoord")
    private Float zCoord;
}
