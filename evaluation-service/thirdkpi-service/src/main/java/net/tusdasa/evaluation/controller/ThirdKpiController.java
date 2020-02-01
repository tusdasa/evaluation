package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.ThirdKpiService;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThirdKpiController {

    private ThirdKpiService thirdKpiService;

    public ThirdKpiController(ThirdKpiService thirdKpiService) {
        this.thirdKpiService = thirdKpiService;

    }

    @GetMapping("/")
    public CommonResponse<ThirdKpi> findAll() {
        return new CommonResponse<ThirdKpi>().ok().table(this.thirdKpiService.findAll());
    }

    @GetMapping("/{id}")
    public CommonResponse<ThirdKpi> findById(@PathVariable("id") Integer id) {
        ThirdKpi thirdKpi = this.thirdKpiService.findThirdKpiById(id);

        if (thirdKpi != null) {
            return new CommonResponse<ThirdKpi>().ok().data(thirdKpi);
        }
        return new CommonResponse<ThirdKpi>().error("未找到");
    }

    @GetMapping("/second/{id}")
    public CommonResponse<ThirdKpi> findSecondKpiId(@PathVariable("id") Integer secondKpiId) {
        return new CommonResponse<ThirdKpi>().ok().table(this.thirdKpiService.findBySecondKpiId(secondKpiId));
    }

    @PostMapping("/")
    public CommonResponse<String> createThirdKpi(@RequestBody ThirdKpiRequest request) {
        if (request.isCreateRequest()) {
            this.thirdKpiService.addThirdKpi(request);
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();
    }

    @PutMapping("/")
    public CommonResponse<String> updateThirdKpi(@RequestBody ThirdKpiRequest request) {
        if (request.isUpdateRequest()) {
            this.thirdKpiService.updateThirdKpi(request);
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();
    }


}
