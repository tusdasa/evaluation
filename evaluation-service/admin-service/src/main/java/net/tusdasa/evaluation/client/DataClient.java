package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.DataClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:26 PM
 */

@FeignClient(value = "evaluation-data-service", path = "/data", fallback = DataClientImpl.class)
public interface DataClient {

    @GetMapping("/department/")
    CommonResponse<Department> findAllDepartment();

    @GetMapping("/major/")
    CommonResponse<Major> findAllMajor();

    @GetMapping("/professional/")
    CommonResponse<Professional> findAllProfessional();

    @GetMapping("/role/")
    CommonResponse<Role> findAllRole();

    @GetMapping("/state/")
    CommonResponse<TeacherState> findAllTeacherState();
}
