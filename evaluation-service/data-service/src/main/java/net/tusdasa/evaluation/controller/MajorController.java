package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Major;
import net.tusdasa.evaluation.service.MajorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MajorController {

    private MajorService majorService;

    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }

    @GetMapping("major/{majorId}")
    public CommonResponse<Major> findMajorById(@PathVariable("majorId") Integer majorId) {
        Major major = this.majorService.findById(majorId);
        if (major != null) {
            return new CommonResponse<Major>().ok().data(major);
        }
        return new CommonResponse<Major>().error("未找到");
    }

    @GetMapping("/major")
    public CommonResponse<Major> findAllMajor() {
        return new CommonResponse<Major>().ok().table(this.majorService.findAll());
    }
}
