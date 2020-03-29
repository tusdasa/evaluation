package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.service.StudentService;
import net.tusdasa.evaluation.vo.StudentRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public CommonResponse<Student> getStudentById(@PathVariable(name = "studentId") Long studentId) {
        Student student = studentService.findStudentById(studentId);
        if (student != null) {
            return new CommonResponse<Student>().ok().data(student);
        } else {
            return new CommonResponse<Student>().error("未找到");
        }
    }

    @PostMapping("/auth")
    public CommonResponse<Student> getStudent(@RequestParam("studentId") Long studentId, @RequestParam("password") String password) {
        Map<String, Object> map = this.studentService.findStudentByIdAndPassword(studentId, password);
        if (map.get("code").equals(1)) {
            return new CommonResponse<Student>().ok().data((Student) map.get("obj"));
        } else {
            return new CommonResponse<Student>().error(map.get("msg").toString());
        }
    }

    /***
     * {
     * "classId": 1,
     * "departmentId": 9,
     * "gradeId": 1,
     * "majorId": 1,
     * "studentId": 1810212128,
     * "studentName": "二维",
     * "studentSecret": "12345678"
     * }
     */

    @PutMapping("/")
    public CommonResponse<String> updateStudent(@RequestBody StudentRequest request) {
        studentService.updateStudent(request);
        return new CommonResponse<String>().ok();
    }

    /***
     * {
     * "classId": 1,
     * "departmentId": 9,
     * "gradeId": 1,
     * "majorId": 1,
     * "studentId": 1810212128,
     * "studentName": "问问",
     * "studentSecret": "12345678"
     * }
     */

    @PostMapping("/")
    public CommonResponse<String> crateStudent(@RequestBody StudentRequest request) {

        if (request.isCreateRequest()) {
            studentService.addStudent(request);
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();
    }


    @GetMapping("/")
    public CommonResponse<Student> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                           @RequestParam(name = "size", defaultValue = "20", required = false) Integer size) {
        return new CommonResponse<Student>().ok().table(this.studentService.findAllByPage(page, size));
    }

    @PutMapping("/rest/{studentId}")
    public CommonResponse<String> restPassword(@PathVariable("studentId") Long studentId, @RequestParam("new") String newPassword, @RequestParam("old") String oldPassword) {
        if (studentService.resetPassword(studentId, newPassword, oldPassword)) {
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();
    }

    @GetMapping("/department/{departmentId}")
    public CommonResponse<Long> countByDepartmentId(@PathVariable("departmentId") Integer departmentId) {
        return new CommonResponse<Long>().ok().data(this.studentService.countByDepartmentId(departmentId));
    }
}
