package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.RightClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Right;
import org.springframework.stereotype.Service;


/**
 * @Author: tusdasa
 * @Date: 2020-03-06 3:07 PM
 */

@Service
public class RightService {

    private RightClient rightClient;

    public RightService(RightClient rightClient) {
        this.rightClient = rightClient;
    }

    public CommonResponse<Right> findAllRight() {
        return this.rightClient.findAll();
    }

    public CommonResponse<Right> findRightById(Integer id) {
        return this.rightClient.findRightById(id);
    }

    public CommonResponse<String> update(Right right) {
        return this.rightClient.updateRight(right);
    }

    public CommonResponse<String> create(Right right) {
        return this.rightClient.createRight(right);
    }
}
