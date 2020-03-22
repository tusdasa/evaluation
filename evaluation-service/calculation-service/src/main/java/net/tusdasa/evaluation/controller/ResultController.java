package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Result;
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
    public CommonResponse<Result> findById(@PathVariable("id") Integer id) {
        //return this.analysisClient.findStudentSituationById(id);
        Result result = this.teacherResultService.getScore(id);
        if (result != null) {
            return new CommonResponse<Result>().ok().data(result);
        }
        return new CommonResponse<Result>().error("未找到");
    }

}
