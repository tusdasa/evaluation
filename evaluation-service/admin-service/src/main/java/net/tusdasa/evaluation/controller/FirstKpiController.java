package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.FirstKpi;
import net.tusdasa.evaluation.service.FirstKpiService;
import net.tusdasa.evaluation.vo.FirstKpiRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 第一指标
 *
 * @Author: tusdasa
 * @Date: 2020-03-07 1:40 PM
 */
@RestController
public class FirstKpiController {

    private FirstKpiService firstKpiService;

    public FirstKpiController(FirstKpiService firstKpiService) {
        this.firstKpiService = firstKpiService;
    }

    @GetMapping("/first")
    public CommonResponse<FirstKpi> findAll() {
        return this.firstKpiService.findAll();
    }

    @GetMapping("/first/{id}")
    public CommonResponse<FirstKpi> findById(@PathVariable("id") Integer id) {
        return this.firstKpiService.findById(id);

    }

    @PutMapping("/first")
    public CommonResponse<String> update(@RequestBody FirstKpiRequest request) {
        return this.firstKpiService.update(request);
    }

    @PostMapping("/first")
    public CommonResponse<String> create(@RequestBody FirstKpiRequest request) {
        return this.firstKpiService.create(request);
    }

    @DeleteMapping("/first/{id}")
    public CommonResponse<String> delete(@PathVariable("id") Integer id) {
        return this.firstKpiService.delete(id);
    }

}
