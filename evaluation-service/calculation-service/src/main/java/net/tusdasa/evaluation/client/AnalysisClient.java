package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.AnalysisClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentSituation;
import net.tusdasa.evaluation.entity.TeacherSituation;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("student/ids")
    CommonResponse<StudentSituation> findStudentSituationByIds(@RequestBody IdsRequest idsRequest);

    @GetMapping("teacher/ids")
    CommonResponse<TeacherSituation> findTeacherSituationByIds(@RequestBody IdsRequest idsRequest);
}
