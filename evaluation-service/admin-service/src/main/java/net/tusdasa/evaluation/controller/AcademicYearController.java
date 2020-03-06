package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.service.AcademicYearService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-03-06 7:55 PM
 */

@RestController
public class AcademicYearController {

    private AcademicYearService academicYearService;

    public AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

}
