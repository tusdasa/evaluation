package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.RightClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Right;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:54 PM
 */

@Component
public class RightClientImpl implements RightClient {
    @Override
    public CommonResponse<Right> findAll() {
        return new CommonResponse<Right>().busy();
    }

    @Override
    public CommonResponse<Right> findRightById(Integer id) {
        return new CommonResponse<Right>().busy();
    }

    @Override
    public CommonResponse<String> updateRight(Right right) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> createRight(Right right) {
        return new CommonResponse<String>().busy();
    }
}
