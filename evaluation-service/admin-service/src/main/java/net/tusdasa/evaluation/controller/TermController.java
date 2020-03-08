package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.service.TermService;
import net.tusdasa.evaluation.vo.TermRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-08 8:51 PM
 */

@RestController
@RequestMapping("/term")
public class TermController {

    private TermService termService;

    public TermController(TermService termService) {
        this.termService = termService;
    }

    @GetMapping("/{id}")
    public CommonResponse<Term> findById(@PathVariable("id") Integer id) {
        return this.termService.findById(id);
    }

    @GetMapping("/")
    public CommonResponse<Term> findAll() {
        return this.termService.findAll();
    }

    @PutMapping("/")
    public CommonResponse<String> update(TermRequest request) {
        return this.termService.update(request);
    }

    @PostMapping("/")
    public CommonResponse<String> create(TermRequest request) {
        return this.termService.create(request);
    }

}
