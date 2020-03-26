package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYearResult;
import net.tusdasa.evaluation.service.CalculateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-03-24 12:58 PM
 */

@RestController
public class CalculateController {

    private CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @GetMapping("/result")
    public CommonResponse<AcademicYearResult> findById(@RequestHeader("workId") Integer id) {
        return calculateService.findById(id);
    }

    @GetMapping("/calculate/{id}")
    public CommonResponse<AcademicYearResult> calculateById(@PathVariable("id") Integer id) {
        return calculateService.findById(id);
    }
}
