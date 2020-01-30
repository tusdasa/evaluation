package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.dao.AcademicYearMapper;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.service.AcademicYearService;
import net.tusdasa.evaluation.vo.AcademicYearRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AcademicYearServiceImpl implements AcademicYearService {

    private AcademicYearMapper academicYearMapper;

    public AcademicYearServiceImpl(AcademicYearMapper academicYearMapper){
        this.academicYearMapper = academicYearMapper;
    }

    @Transactional
    @Override
    public void addAcaAcademicYear(AcademicYearRequest request) {
        academicYearMapper.insert(request.build());
        log.info("add acaAcademicYear {}",request);
    }

    @Transactional
    @Override
    public void updateAcaAcademicYear(AcademicYearRequest request) {
        AcademicYear academicYear = academicYearMapper.selectByPrimaryKey(request.getAcademicYearId());
        if (academicYear!=null && academicYear.compareTo(request)!=0){
            academicYearMapper.updateByPrimaryKey(request.build());
            log.info("update acaAcademicYear {}",request);
        }
    }

    @Transactional
    @Override
    public void deleteAcaAcademicYear(Integer academicYearId) {
        this.academicYearMapper.deleteByPrimaryKey(academicYearId);
        log.info("delete acaAcademicYear {}",academicYearId);
    }

    @Transactional(readOnly = true)
    @Override
    public AcademicYear currentAcademicYear() {
        return this.academicYearMapper.currentAcademicYear();
    }

    @Transactional(readOnly = true)
    @Override
    public List<AcademicYear> findAll() {
        return this.academicYearMapper.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public AcademicYear findAcaAcademicYearById(Integer academicYearId) {
        return this.academicYearMapper.selectByPrimaryKey(academicYearId);
    }
}
