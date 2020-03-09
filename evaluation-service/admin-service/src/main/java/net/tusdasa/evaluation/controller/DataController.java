package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.*;
import net.tusdasa.evaluation.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据管理
 *
 * @Author: tusdasa
 * @Date: 2020-03-09 11:09 AM
 */

@RestController
@RequestMapping("/data")
public class DataController {

    private DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/department")
    public CommonResponse<Department> findAllDepartment() {
        return this.dataService.findAllDepartment();
    }

    @GetMapping("/major")
    public CommonResponse<Role> findAllRole() {
        return this.dataService.findAllRole();
    }

    @GetMapping("/professional")
    public CommonResponse<Professional> findAllProfessional() {
        return this.dataService.findAllProfessional();
    }

    @GetMapping("/role")
    public CommonResponse<Major> findAllMajor() {
        return this.dataService.findAllMajor();
    }

    @GetMapping("/state")
    public CommonResponse<TeacherState> findAllTeacherState() {
        return this.dataService.findAllTeacherState();
    }
}
