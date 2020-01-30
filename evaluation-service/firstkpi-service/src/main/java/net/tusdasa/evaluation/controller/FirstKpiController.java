package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.FirstKpi;
import net.tusdasa.evaluation.service.FirstKpiService;
import net.tusdasa.evaluation.vo.FirstKpiRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirstKpiController {

    private FirstKpiService firstKpiService;

    public FirstKpiController(FirstKpiService firstKpiService) {
        this.firstKpiService = firstKpiService;
    }

    @GetMapping("/academicyear/{id}")
    public CommonResponse<FirstKpi> findAllByFirstKpi(@PathVariable("id") Integer id) {
        return new CommonResponse<FirstKpi>().ok().table(this.firstKpiService.findAllFirstKpiByAcademicYear(id));
    }

    @GetMapping("/")
    public CommonResponse<FirstKpi> findAll() {
        return new CommonResponse<FirstKpi>().ok().table(this.firstKpiService.findAll());
    }

    @GetMapping("/{id}")
    public CommonResponse<FirstKpi> findById(@PathVariable("id") Integer id) {
        FirstKpi firstKpi = this.firstKpiService.findById(id);
        if (firstKpi != null) {
            return new CommonResponse<FirstKpi>().ok().data(this.firstKpiService.findById(id));
        } else {
            return new CommonResponse<FirstKpi>().error("未找到");
        }
    }

    @PutMapping("/")
    public CommonResponse<String> updateFirstKpi(@RequestBody FirstKpiRequest request) {
        if (request.isUpdateRequest()) {
            this.firstKpiService.updateFirstKpi(request);
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();
    }

    @PostMapping("/")
    public CommonResponse<String> createFirstKpi(@RequestBody FirstKpiRequest request) {
        if (request.isCreateRequest()) {
            this.firstKpiService.addFirstKpi(request);
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();
    }

}
