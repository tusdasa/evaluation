package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Role;
import net.tusdasa.evaluation.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{roleId}")
    public CommonResponse<Role> findRoleById(@PathVariable("roleId") Integer roleId) {
        Role role = this.roleService.findById(roleId);
        if (role != null) {
            return new CommonResponse<Role>().ok().data(role);
        }
        return new CommonResponse<Role>().error("未找到");
    }

    @GetMapping("/")
    public CommonResponse<Role> findAllRole() {
        return new CommonResponse<Role>().ok().table(this.roleService.findAll());
    }
}
