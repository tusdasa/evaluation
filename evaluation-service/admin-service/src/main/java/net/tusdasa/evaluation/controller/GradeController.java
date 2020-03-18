package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Grade;
import net.tusdasa.evaluation.service.GradeService;
import net.tusdasa.evaluation.vo.GradeRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-16 4:51 PM
 */

@RestController
@RequestMapping("/grade")
public class GradeController {

    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/")
    public CommonResponse<Grade> findAll() {
        return this.gradeService.findAll();
    }

    @PostMapping("/")
    public CommonResponse<String> create(GradeRequest request) {
        return this.gradeService.create(request);
    }

    @PutMapping("/")
    public CommonResponse<String> update(GradeRequest request) {
        return this.gradeService.update(request);
    }

    @GetMapping("/{id}")
    public CommonResponse<Grade> findById(@PathVariable("id") Integer gradeId) {
        return this.gradeService.findById(gradeId);
    }

}
