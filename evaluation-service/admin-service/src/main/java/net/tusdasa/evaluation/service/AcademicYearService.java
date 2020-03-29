package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.vo.AcademicYearRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-06 7:56 PM
 */

@Service
public class AcademicYearService {

    private AcademicYearClient academicYearClient;

    public AcademicYearService(AcademicYearClient academicYearClient) {
        this.academicYearClient = academicYearClient;
    }

    public CommonResponse<AcademicYear> findAll() {
        return this.academicYearClient.findAll();
    }

    public CommonResponse<AcademicYear> findById(Integer id) {
        return this.academicYearClient.findByAcademicYearId(id);
    }

    public CommonResponse<String> update(AcademicYearRequest request) {
        return this.academicYearClient.updateAcademicYear(request);
    }

    public CommonResponse<String> create(AcademicYearRequest request) {
        return this.academicYearClient.createAcademicYear(request);
    }

    public CommonResponse<AcademicYear> current() {
        return this.academicYearClient.current();
    }


}
