package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.ThirdKpiService;
import net.tusdasa.evaluation.vo.IdsRequest;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThirdKpiController {

    private RedisTemplate<String, List<ThirdKpi>> redisTemplate;

    private ThirdKpiService thirdKpiService;

    public ThirdKpiController(ThirdKpiService thirdKpiService, RedisTemplate<String, List<ThirdKpi>> redisTemplate) {
        this.thirdKpiService = thirdKpiService;
        this.redisTemplate = redisTemplate;

    }

    @GetMapping("/")
    public CommonResponse<ThirdKpi> findAll() {
        List<ThirdKpi> thirdKpiList = this.thirdKpiService.findAll();
        return new CommonResponse<ThirdKpi>().ok().table(thirdKpiList);
    }

    @GetMapping("/{thirdKpiId}")
    public CommonResponse<ThirdKpi> findThirdKpiById(@PathVariable("thirdKpiId") Integer thirdKpiId) {
        ThirdKpi thirdKpi = this.thirdKpiService.findThirdKpiById(thirdKpiId);

        if (thirdKpi != null) {
            return new CommonResponse<ThirdKpi>().ok().data(thirdKpi);
        }
        return new CommonResponse<ThirdKpi>().error("未找到");
    }

    @GetMapping("/second/{secondKpiId}")
    public CommonResponse<ThirdKpi> findAllBySecondKpiId(@PathVariable("secondKpiId") Integer secondKpiId) {
        return new CommonResponse<ThirdKpi>().ok().table(this.thirdKpiService.findAllBySecondKpiId(secondKpiId));
    }

    @PostMapping("/second")
    public CommonResponse<ThirdKpi> findAllBySecondKpiIds(@RequestBody IdsRequest idsRequest) {
        return new CommonResponse<ThirdKpi>().ok().table(this.thirdKpiService.findAllBySecondKpiIds(idsRequest));
    }

    @PostMapping("/ids/{secondKpiId}")
    public CommonResponse<ThirdKpi> findAllBySecondKpiIdAndThirdKpiIds(@PathVariable("secondKpiId") Integer secondKpiId, @RequestBody IdsRequest idsRequest) {
        return new CommonResponse<ThirdKpi>().ok().table(this.thirdKpiService.findAllBySecondKpiIdAndThirdKpiIds(secondKpiId, idsRequest));
    }

    @PostMapping("/ids/ids")
    public CommonResponse<ThirdKpi> findAllBySecondKpiIdsAndThirdKpiIds(@RequestBody IdsRequest idsRequest) {
        return new CommonResponse<ThirdKpi>().ok().table(this.thirdKpiService.findAllBySecondKpiIdsAndThirdKpiIds(idsRequest));
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
