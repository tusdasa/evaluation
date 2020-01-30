package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MajorMapper extends BaseDao<Major, Integer> {
}