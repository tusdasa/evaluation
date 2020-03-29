package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.MajorMapper;
import net.tusdasa.evaluation.entity.Major;
import net.tusdasa.evaluation.service.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MajorServiceImpl implements MajorService {

    private MajorMapper majorMapper;

    public MajorServiceImpl(MajorMapper majorMapper) {
        this.majorMapper = majorMapper;
    }

    @Override
    public List<Major> findAll() {
        return majorMapper.findAll();
    }

    @Override
    public Major findById(Integer majorId) {
        return majorMapper.selectByPrimaryKey(majorId);
    }
}
