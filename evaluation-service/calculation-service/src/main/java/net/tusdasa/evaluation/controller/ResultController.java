package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.client.AnalysisClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentSituation;
import net.tusdasa.evaluation.service.TeacherResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 5:19 PM
 */

@RestController
public class ResultController {

    private AnalysisClient analysisClient;

    private TeacherResultService teacherResultService;

    public ResultController(AnalysisClient analysisClient, TeacherResultService teacherResultService) {
        this.analysisClient = analysisClient;
        this.teacherResultService = teacherResultService;
    }

    @GetMapping("/{id}")
    public CommonResponse<StudentSituation> findById(@PathVariable("id") Integer id) {
        this.teacherResultService.getScore(id);
        return this.analysisClient.findStudentSituationById(id);
    }

}
