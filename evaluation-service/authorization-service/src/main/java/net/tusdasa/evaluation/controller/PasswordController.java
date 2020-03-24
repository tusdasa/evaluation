package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.service.RestPasswordService;
import net.tusdasa.evaluation.utils.CheckUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-03-24 3:54 PM
 */

@RestController
public class PasswordController {

    private RestPasswordService restPasswordService;

    public PasswordController(RestPasswordService restPasswordService) {
        this.restPasswordService = restPasswordService;
    }

    @PutMapping("/rest/teacher")
    public CommonResponse<String> restTeacherPassword(@RequestHeader("workId") Integer workId,
                                                      @RequestParam("new") String newPassword,
                                                      @RequestParam("old") String oldPassword) {
        if (CheckUtils.isPassword(newPassword) && CheckUtils.isPassword(oldPassword)) {
            return this.restPasswordService.restTeacherPassword(workId, newPassword, oldPassword);
        }
        return new CommonResponse<String>().error("密码不符合规范");
    }

    @PutMapping("/rest/student")
    public CommonResponse<String> restStudentPassword(@RequestHeader("studentId") Long studentId,
                                                      @RequestParam("new") String newPassword,
                                                      @RequestParam("old") String oldPassword) {
        if (CheckUtils.isPassword(newPassword) && CheckUtils.isPassword(oldPassword)) {
            return this.restPasswordService.restStudentPassword(studentId, newPassword, oldPassword);
        }
        return new CommonResponse<String>().error("密码不符合规范");
    }
}
