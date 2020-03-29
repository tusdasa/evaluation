package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.service.TermService;
import net.tusdasa.evaluation.vo.TermRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 学期管理
 *
 * @Author: tusdasa
 * @Date: 2020-03-08 8:51 PM
 */

@RestController
public class TermController {

    private TermService termService;

    public TermController(TermService termService) {
        this.termService = termService;
    }

    @GetMapping("/term/{id}")
    public CommonResponse<Term> findById(@PathVariable("id") Integer id) {
        return this.termService.findById(id);
    }

    @GetMapping("/term")
    public CommonResponse<Term> findAll() {
        return this.termService.findAll();
    }

    @PutMapping("/term")
    public CommonResponse<String> update(TermRequest request) {
        return this.termService.update(request);
    }

    @PostMapping("/term")
    public CommonResponse<String> create(TermRequest request) {
        return this.termService.create(request);
    }

}
