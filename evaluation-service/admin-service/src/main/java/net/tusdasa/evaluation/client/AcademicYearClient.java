package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.AcademicYearClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.vo.AcademicYearRequest;
import net.tusdasa.evaluation.vo.TermRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "evaluation-academic-service", path = "/academic", fallback = AcademicYearClientImpl.class)
public interface AcademicYearClient {

    @GetMapping("/")
    CommonResponse<AcademicYear> findAll();

    @GetMapping("/current")
    CommonResponse<AcademicYear> current();

    @GetMapping("/term")
    CommonResponse<Term> currentTerm();

    @PutMapping("/")
    CommonResponse<String> updateAcademicYear(@RequestBody AcademicYearRequest request);

    @PostMapping("/")
    CommonResponse<String> createAcademicYear(@RequestBody AcademicYearRequest request);

    @GetMapping("/{academicYearId}")
    CommonResponse<AcademicYear> findByAcademicYearId(@PathVariable("academicYearId") Integer academicYearId);

    @GetMapping("/term/")
    CommonResponse<Term> findAllTerm();

    @GetMapping("/term/{termId}")
    CommonResponse<Term> findById(@PathVariable("termId") Integer termId);

    @PostMapping("/term/")
    CommonResponse<String> createTerm(@RequestBody TermRequest request);

    @PutMapping("/term/")
    CommonResponse<String> updateTerm(@RequestBody TermRequest request);

}
