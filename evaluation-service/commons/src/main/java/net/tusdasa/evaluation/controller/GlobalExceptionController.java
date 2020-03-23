package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(Exception.class)
    public CommonResponse<String> AllExceptionHandler(Exception e) {
        return new CommonResponse<String>().error(e.getMessage());
    }
}
