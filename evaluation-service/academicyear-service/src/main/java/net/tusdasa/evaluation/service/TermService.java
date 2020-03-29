package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.vo.TermRequest;

import java.util.List;

/**
 * 伴随学年添加而添加
 */
public interface TermService {

    void addTerm(TermRequest request);

    void deleteTerm(Integer termId);

    void updateTerm(TermRequest request);

    Term findById(Integer termId);

    List<Term> findAll();
}
