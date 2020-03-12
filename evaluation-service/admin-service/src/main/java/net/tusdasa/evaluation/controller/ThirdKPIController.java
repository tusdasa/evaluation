package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.authority.Authority;
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
@RequestMapping("/third")
public class ThirdKPIController {

    private ThirdKPIService thirdKPIService;

    public ThirdKPIController(ThirdKPIService thirdKPIService) {
        this.thirdKPIService = thirdKPIService;
    }

    @GetMapping("/")
    public CommonResponse<ThirdKpi> findAll(@RequestHeader("role") Integer roleId) {
        if (!roleId.equals(Authority.ADMIN)) {
            return new CommonResponse<ThirdKpi>().error("未授权");
        }
        return this.thirdKPIService.findAll();
    }

    @GetMapping("/{id}")
    public CommonResponse<ThirdKpi> findById(@PathVariable("id") Integer id, @RequestHeader("role") Integer roleId) {
        if (!roleId.equals(Authority.ADMIN)) {
            return new CommonResponse<ThirdKpi>().error("未授权");
        }
        return this.thirdKPIService.findById(id);
    }

    @PutMapping("/")
    public CommonResponse<String> update(@RequestBody ThirdKpiRequest request, @RequestHeader("role") Integer roleId) {
        if (!roleId.equals(Authority.ADMIN)) {
            return new CommonResponse<String>().error("未授权");
        }
        return this.thirdKPIService.update(request);
    }

    @PostMapping("/")
    public CommonResponse<String> create(@RequestBody ThirdKpiRequest request, @RequestHeader("role") Integer roleId) {
        if (!roleId.equals(Authority.ADMIN)) {
            return new CommonResponse<String>().error("未授权");
        }
        return this.thirdKPIService.create(request);
    }

    @DeleteMapping("/{id}")
    public CommonResponse<String> delete(@PathVariable("id") Integer id, @RequestHeader("role") Integer roleId) {
        if (!roleId.equals(Authority.ADMIN)) {
            return new CommonResponse<String>().error("未授权");
        }
        return this.thirdKPIService.delete(id);
    }
}
