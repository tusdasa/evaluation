package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Evidence;
import net.tusdasa.evaluation.service.EvidenceService;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: tusdasa
 * @Date: 2020-03-02 5:36 PM
 */

@RestController
@RequestMapping("/evidence")
public class EvidenceController {

    private EvidenceService evidenceService;

    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @GetMapping("/{id}")
    public CommonResponse<Evidence> findEvidenceById(@PathVariable("id") Integer id) {
        Evidence evidence = this.evidenceService.findById(id);

        if (evidence != null) {
            return new CommonResponse<Evidence>().ok().data(evidence);
        }
        return new CommonResponse<Evidence>().error("不存在");
    }

    @GetMapping("/")
    public CommonResponse<Evidence> findAllEvidence() {
        return new CommonResponse<Evidence>().ok().table(this.evidenceService.findAll());
    }

    @PostMapping("/")
    public CommonResponse<String> createEvidence(@RequestBody Evidence evidence) {
        this.evidenceService.addEvidence(evidence);
        return new CommonResponse<String>().ok();
    }

    @PutMapping("/")
    public CommonResponse<String> updateEvidence(@RequestBody Evidence evidence) {
        this.evidenceService.updateEvidence(evidence);
        return new CommonResponse<String>().ok();
    }
}
