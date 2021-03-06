package net.tusdasa.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.dao.AcademicYearMapper;
import net.tusdasa.evaluation.entity.AcademicYear;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.service.AcademicYearService;
import net.tusdasa.evaluation.vo.AcademicYearRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AcademicYearServiceImpl implements AcademicYearService {

    private AcademicYearMapper academicYearMapper;

    public AcademicYearServiceImpl(AcademicYearMapper academicYearMapper) {
        this.academicYearMapper = academicYearMapper;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "currentAcademicYear", allEntries = true),
                    @CacheEvict(value = "findAll", allEntries = true),
                    @CacheEvict(value = "findAcaAcademicYearById", allEntries = true),
                    @CacheEvict(value = "currentTerm", allEntries = true)
            }
    )
    @Transactional
    @Override
    public void addAcaAcademicYear(AcademicYearRequest request) {
        academicYearMapper.insert(request.build());
        log.info("add acaAcademicYear {}", request);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "currentAcademicYear", allEntries = true),
                    @CacheEvict(value = "findAll", allEntries = true),
                    @CacheEvict(value = "findAcaAcademicYearById", allEntries = true),
                    @CacheEvict(value = "currentTerm", allEntries = true)
            }
    )
    @Transactional
    @Override
    public void updateAcaAcademicYear(AcademicYearRequest request) {
        AcademicYear academicYear = academicYearMapper.selectByPrimaryKey(request.getAcademicYearId());
        if (academicYear != null && academicYear.compareTo(request) != 0) {
            academicYearMapper.updateByPrimaryKey(request.build());
            log.info("update acaAcademicYear {}", request);
        }
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "currentAcademicYear", allEntries = true),
                    @CacheEvict(value = "findAll", allEntries = true),
                    @CacheEvict(value = "findAcaAcademicYearById", allEntries = true),
                    @CacheEvict(value = "currentTerm", allEntries = true)
            }
    )
    @Transactional
    @Override
    public void deleteAcaAcademicYear(Integer academicYearId) {
        this.academicYearMapper.deleteByPrimaryKey(academicYearId);
        log.info("delete acaAcademicYear {}", academicYearId);
    }

    @Cacheable(value = "currentAcademicYear", key = "methodName")
    @Transactional(readOnly = true)
    @Override
    public AcademicYear currentAcademicYear() {
        return this.academicYearMapper.currentAcademicYear(new Date());
    }

    @Cacheable(value = "findAll", key = "methodName")
    @Transactional(readOnly = true)
    @Override
    public List<AcademicYear> findAll() {
        return this.academicYearMapper.findAll();
    }

    @Cacheable(value = "findAcaAcademicYearById", key = "methodName + #academicYearId")
    @Transactional(readOnly = true)
    @Override
    public AcademicYear findAcaAcademicYearById(Integer academicYearId) {
        return this.academicYearMapper.selectByPrimaryKey(academicYearId);
    }

    @Cacheable("currentTerm")
    @Transactional(readOnly = true)
    @Override
    public Term currentTerm() {
        AcademicYear academicYear = this.academicYearMapper.currentAcademicYear(new Date());
        return this.currentTerm(academicYear);
    }

    private Term currentTerm(AcademicYear academicYear) {
        Date currentDate = new Date();

        // 本学年第一学期
        Term firstTerm = academicYear.getStartTerm();
        // 本学年第二学期
        Term secondTerm = academicYear.getEndTerm();

        if (currentDate.after(firstTerm.getStartTime()) && currentDate.before(firstTerm.getEndTime())) {
            return firstTerm;
        }
        if (currentDate.after(secondTerm.getStartTime()) && currentDate.before(secondTerm.getEndTime())) {
            return secondTerm;
        }

        return null;
    }
}
