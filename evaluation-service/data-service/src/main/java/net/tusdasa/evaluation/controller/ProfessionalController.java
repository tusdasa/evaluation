package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Professional;
import net.tusdasa.evaluation.service.ProfessionalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProfessionalController {
    private ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/professional/{professionalId}")
    public CommonResponse<Professional> findProfessionalById(@PathVariable("professionalId") Integer professionalId) {
        Professional professional = this.professionalService.findById(professionalId);
        if (professional != null) {
            return new CommonResponse<Professional>().data(professional);
        }
        return new CommonResponse<Professional>().error("未找到");
    }

    @GetMapping("/professional")
    public CommonResponse<Professional> findAllProfessional() {
        return new CommonResponse<Professional>().ok().table(this.professionalService.findAll());
    }
}
