package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.service.AcademicYearService;
import net.tusdasa.evaluation.vo.AcademicYearRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 学年管理
 *
 * @Author: tusdasa
 * @Date: 2020-03-06 7:55 PM
 */

@RestController
public class AcademicYearController {

    private AcademicYearService academicYearService;

    public AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

    @GetMapping("/academicyear/{id}")
    public CommonResponse<AcademicYear> findById(@PathVariable("id") Integer id) {
        return this.academicYearService.findById(id);
    }

    @GetMapping("/academicyear")
    public CommonResponse<AcademicYear> findAll() {
        return this.academicYearService.findAll();
    }

    @PutMapping("/academicyear")
    public CommonResponse<String> update(@RequestBody AcademicYearRequest request) {
        return this.academicYearService.update(request);
    }

    @PostMapping("/academicyear")
    public CommonResponse<String> create(@RequestBody AcademicYearRequest request) {
        return this.academicYearService.create(request);
    }

    @GetMapping("/academicyear/current")
    public CommonResponse<AcademicYear> current() {
        return this.academicYearService.current();
    }

}
