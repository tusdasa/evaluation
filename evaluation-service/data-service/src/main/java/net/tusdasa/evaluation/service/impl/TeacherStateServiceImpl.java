package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.TeacherStateMapper;
import net.tusdasa.evaluation.entity.TeacherState;
import net.tusdasa.evaluation.service.TeacherStateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeacherStateServiceImpl implements TeacherStateService {

    private TeacherStateMapper teacherStateMapper;

    public TeacherStateServiceImpl(TeacherStateMapper teacherStateMapper) {
        this.teacherStateMapper = teacherStateMapper;
    }

    @Override
    public List<TeacherState> findAll() {
        return teacherStateMapper.findAll();
    }

    @Override
    public TeacherState findById(Integer id) {
        return teacherStateMapper.selectByPrimaryKey(id);
    }
}
