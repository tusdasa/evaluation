package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.AnalysisClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentSituation;
import net.tusdasa.evaluation.entity.TeacherSituation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 4:46 PM
 */

@FeignClient(value = "evaluation-analysis-service", path = "/analysis", fallback = AnalysisClientImpl.class)
public interface AnalysisClient {

    @GetMapping("student/{id}")
    CommonResponse<StudentSituation> findStudentSituationById(@PathVariable("id") Integer id);

    @GetMapping("teacher/{id}")
    CommonResponse<TeacherSituation> findTeacherSituationById(@PathVariable("id") Integer id);
}
