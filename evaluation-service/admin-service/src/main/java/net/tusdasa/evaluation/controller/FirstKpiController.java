package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.FirstKpi;
import net.tusdasa.evaluation.service.FirstKpiService;
import net.tusdasa.evaluation.vo.FirstKpiRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-07 1:40 PM
 */
@RestController
@RequestMapping("/first")
public class FirstKpiController {

    private FirstKpiService firstKpiService;

    public FirstKpiController(FirstKpiService firstKpiService) {
        this.firstKpiService = firstKpiService;
    }

    @GetMapping("/")
    public CommonResponse<FirstKpi> findAll() {
        return this.firstKpiService.findAll();
    }

    @GetMapping("/{id}")
    public CommonResponse<FirstKpi> findById(@PathVariable("id") Integer id) {
        return this.firstKpiService.findById(id);
    }

    @PutMapping("/")
    public CommonResponse<String> update(FirstKpiRequest request) {
        return this.firstKpiService.update(request);
    }

    @PostMapping("/")
    public CommonResponse<String> create(FirstKpiRequest request) {
        return this.firstKpiService.create(request);
    }
}
