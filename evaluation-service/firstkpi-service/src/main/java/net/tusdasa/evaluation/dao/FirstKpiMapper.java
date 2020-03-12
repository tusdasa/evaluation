package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.FirstKpi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FirstKpiMapper extends BaseDao<FirstKpi, Integer> {

    //List<FirstKpi> findAllByAcademicYear(Integer academicYearId);

    //List<FirstKpi> findAllByAcademicYearAndId(Map<String, Integer> parameter);

    //List<FirstKpi> findAllByAcademicYearAndIds(Map<String, Object> parameter);
}