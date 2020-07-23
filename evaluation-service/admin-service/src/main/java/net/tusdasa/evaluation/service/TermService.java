package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.vo.TermRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-08 6:34 PM
 */

@Service
public class TermService {

    private AcademicYearClient academicYearClient;

    public TermService(AcademicYearClient academicYearClient) {
        this.academicYearClient = academicYearClient;
    }

    public CommonResponse<Term> findAll() {
        return this.academicYearClient.findAllTerm();
    }

    public CommonResponse<Term> findById(Integer id) {
        return this.academicYearClient.findById(id);
    }

    public CommonResponse<String> update(TermRequest request) {
        return this.academicYearClient.updateTerm(request);
    }

    public CommonResponse<String> create(TermRequest request) {
        return this.academicYearClient.createTerm(request);
    }
}
