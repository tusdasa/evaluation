package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.ThirdKPIService;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 第三指标
 *
 * @Author: tusdasa
 * @Date: 2020-03-08 4:46 PM
 */

@RestController
public class ThirdKPIController {

    private ThirdKPIService thirdKPIService;

    public ThirdKPIController(ThirdKPIService thirdKPIService) {
        this.thirdKPIService = thirdKPIService;
    }

    @GetMapping("/third")
    public CommonResponse<ThirdKpi> findAll() {
        return this.thirdKPIService.findAll();
    }

    @GetMapping("/third/{id}")
    public CommonResponse<ThirdKpi> findById(@PathVariable("id") Integer id) {
        return this.thirdKPIService.findById(id);
    }

    @PutMapping("/third")
    public CommonResponse<String> update(@RequestBody ThirdKpiRequest request) {
        return this.thirdKPIService.update(request);
    }

    @PostMapping("/third")
    public CommonResponse<String> create(@RequestBody ThirdKpiRequest request) {
        return this.thirdKPIService.create(request);
    }

    @DeleteMapping("/third/{id}")
    public CommonResponse<String> delete(@PathVariable("id") Integer id) {
        return this.thirdKPIService.delete(id);
    }
}
