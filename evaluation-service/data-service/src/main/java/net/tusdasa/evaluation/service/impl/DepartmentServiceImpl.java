package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.DepartmentMapper;
import net.tusdasa.evaluation.entity.Department;
import net.tusdasa.evaluation.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }
}
