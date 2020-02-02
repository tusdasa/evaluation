package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.service.SecondKpiService;
import net.tusdasa.evaluation.vo.SecondKpiRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecondKpiController {

    private SecondKpiService secondKpiService;

    public SecondKpiController(SecondKpiService secondKpiService) {
        this.secondKpiService = secondKpiService;
    }


    @GetMapping("/")
    public CommonResponse<SecondKpi> findAll() {
        return new CommonResponse<SecondKpi>().ok().table(this.secondKpiService.findAll());
    }


    @GetMapping("/{secondKpiId}")
    public CommonResponse<SecondKpi> findById(@PathVariable("secondKpiId") Integer secondKpiId) {
        SecondKpi secondKpi = this.secondKpiService.findSecondKpiById(secondKpiId);
        if (secondKpi != null) {
            return new CommonResponse<SecondKpi>().ok().data(secondKpi);
        }
        return new CommonResponse<SecondKpi>().error();
    }

    @GetMapping("/first/{firstId}")
    public CommonResponse<SecondKpi> findByFirstId(@PathVariable("firstId") Integer firstId) {
        return new CommonResponse<SecondKpi>().ok().table(this.secondKpiService.findAllByFirstKpi(firstId));
    }

    @PostMapping("/")
    public CommonResponse<String> createSecondKpi(@RequestBody SecondKpiRequest request) {
        if (request.isCreateRequest()) {
            this.secondKpiService.addSecondKpi(request);
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();
    }

    @PutMapping("/")
    public CommonResponse<String> updateSecondKpi(@RequestBody SecondKpiRequest request) {
        if (request.isUpdateRequest()) {
            this.secondKpiService.updateSecondKpi(request);
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();
    }


}
