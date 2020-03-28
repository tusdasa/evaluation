package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYearResult;
import net.tusdasa.evaluation.service.CalculateService;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/calculate/ids")
    public CommonResponse<AcademicYearResult> calculateByIds(@RequestBody IdsRequest idsRequest) {
        if (idsRequest.getFirstIds().isEmpty()) {
            return new CommonResponse<AcademicYearResult>().error();
        }
        return calculateService.findByIds(idsRequest);
    }
}
