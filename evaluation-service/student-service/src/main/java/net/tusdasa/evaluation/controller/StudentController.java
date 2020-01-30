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

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public CommonResponse<Student> getStudentById(@PathVariable(name = "id") Long id){
        Student student = studentService.findStudentById(id);
        if (student!=null){
            return new CommonResponse<Student>().ok().data(student);
        }else {
            return new CommonResponse<Student>().error("未找到");
        }
    }

    @GetMapping("/{id}/{password}")
    public CommonResponse<Student> getStudent(@PathVariable(name = "id") Long id, @PathVariable("password") String password){
        Map<String, Object> map = this.studentService.findStudentByIdAndPassword(id,password);
        if (map.get("code").equals(1)){
            return new CommonResponse<Student>().ok().data((Student) map.get("obj"));
        }else {
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
     * "studentName": "爸爸",
     * "studentSecret": "12345678"
     * }
     */

    @PutMapping("/")
    public CommonResponse<Student> updateStudent(@RequestBody StudentRequest request){
        if (request.isUpdateRequest()){
            studentService.updateStudent(request);
            return new CommonResponse<Student>().ok();
        }else {
            return new CommonResponse<Student>().error();
        }

    }

    /***
     * {
     * "classId": 1,
     * "departmentId": 9,
     * "gradeId": 1,
     * "majorId": 1,
     * "studentId": 1810212128,
     * "studentName": "爸爸",
     * "studentSecret": "12345678"
     * }
     */

    @PostMapping("/")
    public CommonResponse<Student> crateStudent(@RequestBody StudentRequest request){

        if (request.isCreateRequest()){
            studentService.addStudent(request);
            return new CommonResponse<Student>().ok();
        }else {
            return new CommonResponse<Student>().error();
        }
    }
}
