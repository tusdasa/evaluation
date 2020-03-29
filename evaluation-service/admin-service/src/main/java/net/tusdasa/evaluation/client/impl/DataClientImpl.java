package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.DataClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.*;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:53 PM
 */

@Component
public class DataClientImpl implements DataClient {
    @Override
    public CommonResponse<Department> findAllDepartment() {
        return new CommonResponse<Department>().busy();
    }

    @Override
    public CommonResponse<Major> findAllMajor() {
        return new CommonResponse<Major>().busy();
    }

    @Override
    public CommonResponse<Professional> findAllProfessional() {
        return new CommonResponse<Professional>().busy();
    }

    @Override
    public CommonResponse<Role> findAllRole() {
        return new CommonResponse<Role>().busy();
    }

    @Override
    public CommonResponse<TeacherState> findAllTeacherState() {
        return new CommonResponse<TeacherState>().busy();
    }
}
