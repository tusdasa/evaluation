package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.commons.CommonResponse;

/**
 * @Author: tusdasa
 * @Date: 2020-03-24 4:52 PM
 */

public interface RestPasswordService {
    CommonResponse<String> restTeacherPassword(Integer workId, String newPassword, String oldPassword);

    CommonResponse<String> restStudentPassword(Long studentId, String newPassword, String oldPassword);
}
