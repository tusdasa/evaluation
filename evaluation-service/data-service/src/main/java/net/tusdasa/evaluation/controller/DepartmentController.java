package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Department;
import net.tusdasa.evaluation.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}")
    public CommonResponse<Department> findDepartmentById(@PathVariable("departmentId") Integer departmentId) {
        Department department = this.departmentService.findById(departmentId);
        if (department != null) {
            return new CommonResponse<Department>().ok().data(department);
        }
        return new CommonResponse<Department>().error("未找到");
    }

    @GetMapping("/")
    public CommonResponse<Department> findAllDepartment() {
        return new CommonResponse<Department>().ok().table(this.departmentService.findAll());
    }
}
