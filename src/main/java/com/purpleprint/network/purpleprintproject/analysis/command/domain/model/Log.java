package com.purpleprint.network.purpleprintproject.analysis.command.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * <pre>
 * Class : Log
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-09       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_log")
public class Log {

    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "log_x_coord")
    private Float xCoord;

    @Column(name = "log_z_coord")
    private Float zCoord;

    @Column(name = "child_id")
    private int childId;

    @Column(name = "log_at")
    private Date logAt;
}
