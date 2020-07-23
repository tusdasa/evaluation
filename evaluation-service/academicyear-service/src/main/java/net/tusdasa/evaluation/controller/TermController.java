package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.service.TermService;
import net.tusdasa.evaluation.vo.TermRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/term")
public class TermController {

    private TermService termService;

    public TermController(TermService termService) {
        this.termService = termService;
    }

    @GetMapping("/")
    public CommonResponse<Term> findAllTerm() {
        return new CommonResponse<Term>().ok().table(this.termService.findAll());
    }

    @GetMapping("/{termId}")
    public CommonResponse<Term> findById(@PathVariable("termId") Integer termId) {
        Term term = this.termService.findById(termId);
        if (term != null) {
            return new CommonResponse<Term>().ok().data(term);
        }
        return new CommonResponse<Term>().error("未找到");
    }

    @PostMapping("/")
    public CommonResponse<String> createTerm(@RequestBody TermRequest request) {
        this.termService.addTerm(request);
        return new CommonResponse<String>().ok();
    }


    @PutMapping("/")
    public CommonResponse<String> updateTerm(@RequestBody TermRequest request) {
        this.termService.updateTerm(request);
        return new CommonResponse<String>().ok();
    }

    @DeleteMapping("/{termId}")
    public CommonResponse<String> deleteTerm(@PathVariable("termId") Integer termId){
        this.termService.deleteTerm(termId);
        return new CommonResponse<String>().ok();
    }

}
