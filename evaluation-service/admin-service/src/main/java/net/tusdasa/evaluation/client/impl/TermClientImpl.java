package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.TermClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.vo.TermRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:55 PM
 */

@Component
public class TermClientImpl implements TermClient {
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
