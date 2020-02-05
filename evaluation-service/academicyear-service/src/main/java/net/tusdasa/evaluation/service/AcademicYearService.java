package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.vo.AcademicYearRequest;

import java.util.List;

/**
 * 每年需要增加一次
 */
public interface AcademicYearService {

    void addAcaAcademicYear(AcademicYearRequest request);

    void updateAcaAcademicYear(AcademicYearRequest request);

    void deleteAcaAcademicYear(Integer academicYearId);

    AcademicYear currentAcademicYear();

    List<AcademicYear> findAll();

    AcademicYear findAcaAcademicYearById(Integer academicYearId);

    Term currentTerm();
}
