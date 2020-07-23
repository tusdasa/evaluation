package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.vo.AcademicYearRequest;
import net.tusdasa.evaluation.vo.TermRequest;
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
    public CommonResponse<Term> currentTerm() {
        return new CommonResponse<Term>().busy();
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

    @Override
    public CommonResponse<Term> findAllTerm() {
        return new CommonResponse<Term>().busy();
    }

    @Override
    public CommonResponse<Term> findById(Integer termId) {
        return new CommonResponse<Term>().busy();
    }

    @Override
    public CommonResponse<String> createTerm(TermRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> updateTerm(TermRequest request) {
        return new CommonResponse<String>().busy();
    }
}
