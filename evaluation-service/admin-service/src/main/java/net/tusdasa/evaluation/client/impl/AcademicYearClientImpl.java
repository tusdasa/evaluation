package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.vo.AcademicYearRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:52 PM
 */

@Component
public class AcademicYearClientImpl implements AcademicYearClient {
    @Override
    public CommonResponse<AcademicYear> findAll() {
        return new CommonResponse<AcademicYear>().busy();
    }

    @Override
    public CommonResponse<AcademicYear> current() {
        return new CommonResponse<AcademicYear>().busy();
    }

    @Override
    public CommonResponse<String> updateAcademicYear(AcademicYearRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> createAcademicYear(AcademicYearRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<AcademicYear> findByAcademicYearId(Integer academicYearId) {
        return new CommonResponse<AcademicYear>().busy();
    }
}
