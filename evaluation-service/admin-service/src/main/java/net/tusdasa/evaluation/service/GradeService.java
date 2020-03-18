package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.GradeClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Grade;
import net.tusdasa.evaluation.vo.GradeRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-16 4:47 PM
 */

@Service
public class GradeService {

    private GradeClient gradeClient;

    public GradeService(GradeClient gradeClient) {
        this.gradeClient = gradeClient;
    }

    public CommonResponse<Grade> findAll() {
        return this.gradeClient.findAll();
    }

    public CommonResponse<String> create(GradeRequest request) {
        return this.gradeClient.create(request);
    }

    public CommonResponse<String> update(GradeRequest request) {
        return this.gradeClient.update(request);
    }

    public CommonResponse<Grade> findById(Integer gradeId) {
        return this.gradeClient.findById(gradeId);
    }

}
