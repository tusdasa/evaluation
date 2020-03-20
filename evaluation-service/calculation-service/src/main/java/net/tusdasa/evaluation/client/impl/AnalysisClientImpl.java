package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.AnalysisClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentSituation;
import net.tusdasa.evaluation.entity.TeacherSituation;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 4:47 PM
 */

@Component
public class AnalysisClientImpl implements AnalysisClient {

    public AnalysisClientImpl() {
    }

    @Override
    public CommonResponse<StudentSituation> findStudentSituationById(Integer id) {
        return new CommonResponse<StudentSituation>().busy();
    }

    @Override
    public CommonResponse<TeacherSituation> findTeacherSituation(Integer id) {
        return new CommonResponse<TeacherSituation>().busy();
    }
}
