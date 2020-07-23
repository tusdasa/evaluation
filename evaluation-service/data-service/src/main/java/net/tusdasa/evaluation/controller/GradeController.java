package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Grade;
import net.tusdasa.evaluation.service.GradeService;
import net.tusdasa.evaluation.vo.GradeRequest;
import org.springframework.web.bind.annotation.*;


@RestController
public class GradeController {

    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/grade/{gradeId}")
    public CommonResponse<Grade> findGradeById(@PathVariable("gradeId") Integer gradeId) {
        Grade grade = this.gradeService.findById(gradeId);

        if (grade != null) {
            return new CommonResponse<Grade>().ok().data(grade);
        }
        return new CommonResponse<Grade>().error("未找到");

    }

    @GetMapping("/grade")
    public CommonResponse<Grade> findAllGrade() {
        return new CommonResponse<Grade>().ok().table(this.gradeService.findAll());
    }

    @PostMapping("/grade")
    public CommonResponse<String> createGrade(@RequestBody GradeRequest request) {
        this.gradeService.addGrade(request);
        return new CommonResponse<String>().ok();
    }

    @PutMapping("/grade")
    public CommonResponse<String> updateGrade(@RequestBody GradeRequest request) {
        this.gradeService.updateGrade(request);
        return new CommonResponse<String>().ok();
    }
}
