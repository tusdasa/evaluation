package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Evidence;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EvidenceMapper extends BaseDao<Evidence, Integer> {
    Evidence findAllByEvidenceType(Integer evidenceType);
}