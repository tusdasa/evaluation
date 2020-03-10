package net.tusdasa.evaluation.controller;

import io.swagger.annotations.Api;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Right;
import net.tusdasa.evaluation.service.RightService;
import org.springframework.web.bind.annotation.*;


@RestController
@Api("权限")
public class RightController {
    private RightService rightService;

    public RightController(RightService rightService) {
        this.rightService = rightService;
    }

    /**
     * HashSet<Integer> first = new HashSet<>();
     * first.add(1);
     * first.add(2);
     * first.add(3);
     * first.add(4);
     * first.add(5);
     * first.add(6);
     * Integer[] f1 = first.toArray(new Integer[0]);
     * for (Integer f:f1) {
     * System.out.println("f="+f);
     * }
     */
    @PostMapping("/right")
    public CommonResponse<String> createRight(@RequestBody Right right) {
        rightService.addRight(right);
        return new CommonResponse<String>().ok();
    }

    @GetMapping("/right")
    public CommonResponse<Right> findAll() {
        return new CommonResponse<Right>().ok().table(this.rightService.findAll());
    }

    @GetMapping("/right/{id}")
    public CommonResponse<Right> findRightById(@PathVariable("id") Integer id) {
        Right right = this.rightService.findById(id);
        if (right != null) {
            return new CommonResponse<Right>().ok().data(right);
        }
        return new CommonResponse<Right>().error();
    }

    @PutMapping("/right")
    public CommonResponse<String> updateRight(@RequestBody Right right) {
        this.rightService.updateRight(right);
        return new CommonResponse<String>().ok();
    }

    @DeleteMapping("/right/{id}")
    public CommonResponse<String> deleteRight(@PathVariable("id") Integer id) {
        this.rightService.delRight(id);
        return new CommonResponse<String>().ok();
    }
}
