package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.Evidence;

import java.util.List;

public interface EvidenceService {

    void addEvidence(Evidence evidence);

    void deleteEvidence(Integer evidenceId);

    void updateEvidence(Evidence evidence);

    Evidence findById(Integer evidenceId);

    List<Evidence> findAll();
}
