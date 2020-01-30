package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.AcademicYear;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AcademicYearMapper extends BaseDao<AcademicYear, Integer> {
    AcademicYear currentAcademicYear();
}