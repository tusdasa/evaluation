package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.TermClient;
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

    private TermClient termClient;

    public TermService(TermClient termClient) {
        this.termClient = termClient;
    }

    public CommonResponse<Term> findAll() {
        return this.termClient.findAllTerm();
    }

    public CommonResponse<Term> findById(Integer id) {
        return this.termClient.findById(id);
    }

    public CommonResponse<String> update(TermRequest request) {
        return this.termClient.updateTerm(request);
    }

    public CommonResponse<String> create(TermRequest request) {
        return this.termClient.createTerm(request);
    }
}
