package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.service.AcademicYearService;
import net.tusdasa.evaluation.vo.AcademicYearRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-06 7:55 PM
 */

@RestController
@RequestMapping("/academicyear")
public class AcademicYearController {

    private AcademicYearService academicYearService;

    public AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

    @GetMapping("/{id}")
    public CommonResponse<AcademicYear> findById(@PathVariable("id") Integer id) {
        return this.academicYearService.findById(id);
    }

    @GetMapping("/")
    public CommonResponse<AcademicYear> findAll() {
        return this.academicYearService.findAll();
    }

    @PutMapping("/")
    public CommonResponse<String> update(@RequestBody AcademicYearRequest request) {
        return this.academicYearService.update(request);
    }

    @PostMapping("/")
    public CommonResponse<String> create(@RequestBody AcademicYearRequest request) {
        return this.academicYearService.create(request);
    }

}
