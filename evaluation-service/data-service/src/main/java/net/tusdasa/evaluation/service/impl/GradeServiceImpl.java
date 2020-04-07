package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.dao.GradeMapper;
import net.tusdasa.evaluation.entity.Grade;
import net.tusdasa.evaluation.service.GradeService;
import net.tusdasa.evaluation.vo.GradeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class GradeServiceImpl implements GradeService {

    private GradeMapper gradeMapper;

    public GradeServiceImpl(GradeMapper gradeMapper) {
        this.gradeMapper = gradeMapper;
    }

    @Transactional
    @Override
    public void addGrade(GradeRequest request) {
        this.gradeMapper.insert(request.build());
    }

    @Transactional
    @Override
    public void deleteGrade(Integer gradeId) {
        this.gradeMapper.deleteByPrimaryKey(gradeId);
    }

    @Transactional
    @Override
    public void updateGrade(GradeRequest request) {
        Grade grade = this.gradeMapper.selectByPrimaryKey(request.getGradeId());
        if (grade != null && grade.compareTo(request) != 0) {
            this.gradeMapper.updateByPrimaryKeySelective(request.build());
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Grade findById(Integer gradeId) {
        return this.gradeMapper.selectByPrimaryKey(gradeId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Grade> findAll() {
        return this.gradeMapper.findAll();
    }
}
