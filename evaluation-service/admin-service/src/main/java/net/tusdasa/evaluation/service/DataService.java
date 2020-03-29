package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.DataClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.*;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-09 10:54 AM
 */

@Service
public class DataService {

    private DataClient dataClient;

    public DataService(DataClient dataClient) {
        this.dataClient = dataClient;
    }

    public CommonResponse<Department> findAllDepartment() {
        return this.dataClient.findAllDepartment();
    }

    public CommonResponse<Role> findAllRole() {
        return this.dataClient.findAllRole();
    }

    public CommonResponse<Professional> findAllProfessional() {
        return this.dataClient.findAllProfessional();
    }

    public CommonResponse<Major> findAllMajor() {
        return this.dataClient.findAllMajor();
    }

    public CommonResponse<TeacherState> findAllTeacherState() {
        return this.dataClient.findAllTeacherState();
    }
}
