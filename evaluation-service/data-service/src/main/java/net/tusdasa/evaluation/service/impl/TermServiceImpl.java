package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.dao.TermMapper;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.service.TermService;
import net.tusdasa.evaluation.vo.TermRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TermServiceImpl implements TermService {

    private TermMapper termMapper;

    public TermServiceImpl(TermMapper termMapper) {
        this.termMapper = termMapper;
    }

    @Transactional
    @Override
    public void addTerm(TermRequest request) {
        this.termMapper.insert(request.build());
    }

    @Transactional
    @Override
    public void deleteTerm(Integer termId) {
        this.termMapper.deleteByPrimaryKey(termId);
    }

    @Transactional
    @Override
    public void updateTerm(TermRequest request) {
        Term term = this.termMapper.selectByPrimaryKey(request.getTermId());
        if (term!=null && term.compareTo(request)!=0){
            this.termMapper.updateByPrimaryKeySelective(request.build().withTermId(request.getTermId()));
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Term findById(Integer termId) {
        return this.termMapper.selectByPrimaryKey(termId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Term> findAll() {
        return this.findAll();
    }
}
