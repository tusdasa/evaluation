package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Grade;
import net.tusdasa.evaluation.service.GradeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GradeController {

    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/{gradeId}")
    public CommonResponse<Grade> findById(@PathVariable("gradeId") Integer gradeId) {
        Grade grade = this.gradeService.findById(gradeId);

        if (grade != null) {
            return new CommonResponse<Grade>().ok().data(grade);
        }
        return new CommonResponse<Grade>().error("未找到");

    }

    @GetMapping("/")
    public CommonResponse<Grade> findAll() {
        return new CommonResponse<Grade>().ok().table(this.gradeService.findAll());
    }
}
