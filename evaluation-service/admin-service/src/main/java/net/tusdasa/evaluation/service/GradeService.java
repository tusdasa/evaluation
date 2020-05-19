package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.DataClient;
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

    private DataClient dataClient;

    public GradeService(DataClient dataClient) {
        this.dataClient = dataClient;
    }

    public CommonResponse<Grade> findAll() {
        return this.dataClient.findAllGrade();
    }

    public CommonResponse<String> create(GradeRequest request) {
        return this.dataClient.createGrade(request);
    }

    public CommonResponse<String> update(GradeRequest request) {
        return this.dataClient.updateGrade(request);
    }

    public CommonResponse<Grade> findById(Integer gradeId) {
        return this.dataClient.findGradeById(gradeId);
    }

}
