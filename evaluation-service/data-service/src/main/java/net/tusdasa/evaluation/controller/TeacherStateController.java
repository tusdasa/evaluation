package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.TeacherState;
import net.tusdasa.evaluation.service.TeacherStateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TeacherStateController {

    private TeacherStateService teacherStateService;

    public TeacherStateController(TeacherStateService teacherStateService) {
        this.teacherStateService = teacherStateService;
    }

    @GetMapping("/state/{stateId}")
    public CommonResponse<TeacherState> findTeacherStateById(@PathVariable("stateId") Integer stateId) {
        TeacherState teacherState = this.teacherStateService.findById(stateId);
        if (teacherState != null) {
            return new CommonResponse<TeacherState>().ok().data(teacherState);
        }
        return new CommonResponse<TeacherState>().error("未找到");
    }

    @GetMapping("/state")
    public CommonResponse<TeacherState> findAllTeacherState() {
        return new CommonResponse<TeacherState>().ok().table(this.teacherStateService.findAll());
    }
}
