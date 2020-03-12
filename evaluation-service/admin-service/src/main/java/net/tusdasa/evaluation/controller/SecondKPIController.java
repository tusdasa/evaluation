package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.authority.Authority;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.service.SecondKPIService;
import net.tusdasa.evaluation.vo.SecondKpiRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 第二指标
 *
 * @Author: tusdasa
 * @Date: 2020-03-08 1:54 PM
 */

@RestController
@RequestMapping("/second")
public class SecondKPIController {

    private SecondKPIService secondKPIService;

    public SecondKPIController(SecondKPIService secondKPIService) {
        this.secondKPIService = secondKPIService;
    }

    @GetMapping("/")
    public CommonResponse<SecondKpi> findAll(@RequestHeader("role") Integer roleId) {
        if (!roleId.equals(Authority.ADMIN)) {
            return new CommonResponse<SecondKpi>().error("未授权");
        }
        return this.secondKPIService.findAll();
    }

    @GetMapping("/{id}")
    public CommonResponse<SecondKpi> findById(@PathVariable("id") Integer id, @RequestHeader("role") Integer roleId) {
        if (!roleId.equals(Authority.ADMIN)) {
            return new CommonResponse<SecondKpi>().error("未授权");
        }
        return this.secondKPIService.findById(id);
    }

    @PutMapping("/")
    public CommonResponse<String> update(@RequestBody SecondKpiRequest request, @RequestHeader("role") Integer roleId) {
        if (!roleId.equals(Authority.ADMIN)) {
            return new CommonResponse<String>().error("未授权");
        }
        return this.secondKPIService.update(request);
    }

    @PostMapping("/")
    public CommonResponse<String> create(@RequestBody SecondKpiRequest request, @RequestHeader("role") Integer roleId) {
        if (!roleId.equals(Authority.ADMIN)) {
            return new CommonResponse<String>().error("未授权");
        }
        return this.secondKPIService.create(request);
    }

    @DeleteMapping("/{id}")
    public CommonResponse<String> delete(@PathVariable("id") Integer id, @RequestHeader("role") Integer roleId) {
        if (!roleId.equals(Authority.ADMIN)) {
            return new CommonResponse<String>().error("未授权");
        }
        return this.secondKPIService.delete(id);
    }

}
