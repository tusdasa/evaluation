package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.service.AcademicYearService;
import net.tusdasa.evaluation.vo.AcademicYearRequest;
import org.springframework.web.bind.annotation.*;


@RestController
public class AcademicYearController {

    private AcademicYearService academicYearService;

    public AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

    @GetMapping("/")
    public CommonResponse<AcademicYear> findAll() {
        return new CommonResponse<AcademicYear>().ok().table(this.academicYearService.findAll());
    }

    @GetMapping("/current")
    public CommonResponse<AcademicYear> current() {
        return new CommonResponse<AcademicYear>().ok().data(this.academicYearService.currentAcademicYear());
    }

    @PutMapping("/")
    public CommonResponse<String> updateAcademicYear(@RequestBody AcademicYearRequest request) {
        if (request.isUpdateRequest()) {
            this.academicYearService.updateAcaAcademicYear(request);
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();
    }

    @PostMapping("/")
    public CommonResponse<String> createAcademicYear(@RequestBody AcademicYearRequest request) {
        if (request.isCreateRequest()) {
            this.academicYearService.addAcaAcademicYear(request);
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();

    }

    @GetMapping("/{academicYearId}")
    public CommonResponse<AcademicYear> findById(@PathVariable("academicYearId") Integer academicYearId) {
        AcademicYear academicYear = this.academicYearService.findAcaAcademicYearById(academicYearId);
        if (academicYear != null) {
            return new CommonResponse<AcademicYear>().ok().data(academicYear);
        }
        return new CommonResponse<AcademicYear>().error("未找到");

    }
}
