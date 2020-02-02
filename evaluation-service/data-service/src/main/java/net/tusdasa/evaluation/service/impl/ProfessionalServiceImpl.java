package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.ProfessionalMapper;
import net.tusdasa.evaluation.entity.Professional;
import net.tusdasa.evaluation.service.ProfessionalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProfessionalServiceImpl implements ProfessionalService {

    private ProfessionalMapper professionalMapper;

    public ProfessionalServiceImpl(ProfessionalMapper professionalMapper) {
        this.professionalMapper = professionalMapper;
    }

    @Override
    public List<Professional> findAll() {
        return professionalMapper.findAll();
    }

    @Override
    public Professional findById(Integer professionalId) {
        return professionalMapper.selectByPrimaryKey(professionalId);
    }
}
