package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Level;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LevelMapper extends BaseDao<Level, Integer> {
    List<Level> findAllByLevelType(Integer evidenceType);
}