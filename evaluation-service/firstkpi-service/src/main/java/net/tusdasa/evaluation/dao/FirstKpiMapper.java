package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.FirstKpi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FirstKpiMapper extends BaseDao<FirstKpi, Integer> {
    List<FirstKpi> findAllByAcademicYear(Integer academicYearId);
}