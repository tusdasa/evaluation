package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.AcademicYearClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.Term;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "evaluation-academic-service", path = "/academic", fallback = AcademicYearClientImpl.class)
public interface AcademicYearClient {

    @GetMapping("/current")
    CommonResponse<AcademicYear> current();

    @GetMapping("/term")
    CommonResponse<Term> currentTerm();
}
