package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Right;
import net.tusdasa.evaluation.service.RightService;
import org.springframework.web.bind.annotation.*;

/**
 * 权限
 *
 * @Author: tusdasa
 * @Date: 2020-03-06 3:10 PM
 */

@RestController
public class RightController {

    RightService rightService;

    public RightController(RightService rightService) {
        this.rightService = rightService;
    }

    @GetMapping("/right")
    public CommonResponse<Right> findAll() {
        return this.rightService.findAllRight();
    }

    @GetMapping("right/{id}")
    public CommonResponse<Right> findById(@PathVariable("id") Integer id) {
        return this.rightService.findRightById(id);
    }

    @PutMapping("/right")
    public CommonResponse<String> update(@RequestBody Right right) {
        return this.rightService.update(right);
    }

    @PostMapping("/right")
    public CommonResponse<String> create(@RequestBody Right right) {
        return this.rightService.create(right);
    }

}
