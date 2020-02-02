package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.RoleMapper;
import net.tusdasa.evaluation.entity.Role;
import net.tusdasa.evaluation.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public Role findById(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
