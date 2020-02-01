package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.service.SecondKpiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/{id}")
    public CommonResponse<SecondKpi> findById(@PathVariable("id") Integer id) {
        SecondKpi secondKpi = this.secondKpiService.findSecondKpiById(id);
        if (secondKpi != null) {
            return new CommonResponse<SecondKpi>().ok().data(secondKpi);
        }
        return new CommonResponse<SecondKpi>().error();
    }

    @GetMapping("/first/{id}")
    public CommonResponse<SecondKpi> findByFirstId(@PathVariable("id") Integer firstId) {
        return new CommonResponse<SecondKpi>().ok().table(this.secondKpiService.findAllByFirstKpi(firstId));
    }
}
