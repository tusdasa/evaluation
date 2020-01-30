package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.Grade;
import net.tusdasa.evaluation.vo.GradeRequest;

import java.util.List;

/**
 * 添加新学生时需要添加一次
 * */
public interface GradeService {
    void addGrade(GradeRequest request);
    void deleteGrade(Integer gradeId);
    void updateGrade(GradeRequest request);
    Grade findById(Integer gradeId);
    List<Grade> findAll();
}
