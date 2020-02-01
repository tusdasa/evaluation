package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.ThirdKpiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdKpiController {

    private ThirdKpiService thirdKpiService;

    public ThirdKpiController(ThirdKpiService thirdKpiService) {
        this.thirdKpiService = thirdKpiService;

    }

    @GetMapping("/{id}")
    public CommonResponse<ThirdKpi> findById(@PathVariable("id") Integer id) {
        ThirdKpi thirdKpi = this.thirdKpiService.findThirdKpiById(id);

        if (thirdKpi != null) {
            return new CommonResponse<ThirdKpi>().ok().data(thirdKpi);
        }
        return new CommonResponse<ThirdKpi>().error("未找到");
    }


}
