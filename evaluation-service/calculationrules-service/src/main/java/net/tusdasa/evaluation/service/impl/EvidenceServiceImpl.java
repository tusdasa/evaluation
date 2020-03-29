package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.EvidenceMapper;
import net.tusdasa.evaluation.entity.Evidence;
import net.tusdasa.evaluation.service.EvidenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: tusdasa
 * @Date: 2020-03-02 3:54 PM
 */

@Transactional
@Service
public class EvidenceServiceImpl implements EvidenceService {

    private EvidenceMapper evidenceMapper;

    public EvidenceServiceImpl(EvidenceMapper evidenceMapper) {
        this.evidenceMapper = evidenceMapper;
    }

    @Override
    public void addEvidence(Evidence evidence) {
        this.evidenceMapper.insert(evidence);
    }

    @Override
    public void deleteEvidence(Integer evidenceId) {
        this.evidenceMapper.deleteByPrimaryKey(evidenceId);
    }

    @Override
    public void updateEvidence(Evidence evidence) {
        this.evidenceMapper.updateByPrimaryKey(evidence);
    }

    @Override
    public Evidence findById(Integer evidenceId) {
        return this.evidenceMapper.selectByPrimaryKey(evidenceId);
    }

    @Override
    public List<Evidence> findAll() {
        return this.evidenceMapper.findAll();
    }
}
