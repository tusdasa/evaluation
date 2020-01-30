package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Term;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TermMapper extends BaseDao<Term, Integer> {
}