package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
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

    private TeacherResultService teacherResultService;

    public ResultController(TeacherResultService teacherResultService) {
        this.teacherResultService = teacherResultService;
    }

    @GetMapping("/{id}")
    public CommonResponse<Double> findById(@PathVariable("id") Integer id) {
        //return this.analysisClient.findStudentSituationById(id);
        return new CommonResponse<Double>().ok().data(this.teacherResultService.getScore(id));
    }

}
