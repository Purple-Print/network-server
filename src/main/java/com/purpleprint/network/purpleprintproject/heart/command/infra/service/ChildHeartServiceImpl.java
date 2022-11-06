package com.purpleprint.network.purpleprintproject.heart.command.infra.service;

import com.purpleprint.network.purpleprintproject.auth.command.application.service.UserService;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.heart.command.domain.service.ChildHeartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 * Class : ChildHeartServiceImpl
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
@Service
public class ChildHeartServiceImpl implements ChildHeartService {

    private final UserService userService;

    public ChildHeartServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public int giveHeart(int id) {

        int result = userService.updateChild(id);

        return result;
    }
}
