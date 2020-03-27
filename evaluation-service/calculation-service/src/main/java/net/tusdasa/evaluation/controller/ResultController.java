package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYearResult;
import net.tusdasa.evaluation.service.CalculateResultService;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 5:19 PM
 */

@RestController
public class ResultController {

    private CalculateResultService calculateResultService;

    public ResultController(CalculateResultService calculateResultService) {
        this.calculateResultService = calculateResultService;
    }

    @GetMapping("/{workId}")
    public CommonResponse<AcademicYearResult> findById(@PathVariable("workId") Integer id) {
        AcademicYearResult result = this.calculateResultService.getAcademicYearResult(id);
        if (result != null) {
            return new CommonResponse<AcademicYearResult>().ok().data(result);
        }
        return new CommonResponse<AcademicYearResult>().error("未找到");
    }

    @GetMapping("/{academicYearId}/{workId}")
    public CommonResponse<AcademicYearResult> findByIdAndAcademic(@PathVariable("academicYearId") Integer academicYearId, @PathVariable("workId") Integer id) {
        AcademicYearResult result = this.calculateResultService.getAcademicYearResultByAcademicYear(id, academicYearId);
        if (result != null) {
            return new CommonResponse<AcademicYearResult>().ok().data(result);
        }
        return new CommonResponse<AcademicYearResult>().error("未找到");
    }

    @GetMapping("/ids")
    public CommonResponse<AcademicYearResult> findAllByIds(@RequestBody IdsRequest request) {
        return null;
    }

}
