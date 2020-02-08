package net.tusdasa.evaluation.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.utils.JWTUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    private JWTUtils jwtUtils;

    public CheckController(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/check")
    public CommonResponse<String> check(@RequestParam("token") String token) {
        try {
            this.jwtUtils.check(token);
            return new CommonResponse<String>().ok("认证成功");
        } catch (JWTVerificationException e) {
            return new CommonResponse<String>().error("认证失败");
        }
    }
}
