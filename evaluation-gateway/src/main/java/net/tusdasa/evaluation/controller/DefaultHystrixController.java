package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-03-27 10:59 AM
 */

@RestController
public class DefaultHystrixController {

    public DefaultHystrixController() {
    }

    @RequestMapping("/fallback")
    public CommonResponse<String> defaultFallback() {
        return new CommonResponse<String>().busy();
    }
}
