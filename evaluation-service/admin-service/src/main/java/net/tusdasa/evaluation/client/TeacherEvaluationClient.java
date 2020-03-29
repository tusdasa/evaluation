package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.TeacherEvaluationClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: tusdasa
 * @Date: 2020-03-29 1:19 PM
 */

@FeignClient(value = "evaluation-teacher-evaluation", path = "/tevaluation", fallback = TeacherEvaluationClientImpl.class)
public interface TeacherEvaluationClient {

    @DeleteMapping("/result/{termId}")
    CommonResponse<String> deleteTeacherResult(@PathVariable("termId") Integer termId);

    @GetMapping("/result/{termId}")
    CommonResponse<Long> countAll(@PathVariable("termId") Integer termId);


}
