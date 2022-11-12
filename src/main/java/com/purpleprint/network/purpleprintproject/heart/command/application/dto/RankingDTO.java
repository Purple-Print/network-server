package com.purpleprint.network.purpleprintproject.heart.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * Class : RankingDTO
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       이상학           최초 생성
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingDTO {

    private Integer childId;
    private String childName;
    private Integer givenHeart;
    private Integer ranking;
}
