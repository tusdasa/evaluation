package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.LevelMapper;
import net.tusdasa.evaluation.entity.Level;
import net.tusdasa.evaluation.service.LevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: tusdasa
 * @Date: 2020-03-02 4:28 PM
 */

@Transactional
@Service
public class LevelServiceImpl implements LevelService {

    private LevelMapper levelMapper;

    public LevelServiceImpl(LevelMapper levelMapper) {
        this.levelMapper = levelMapper;
    }

    @Override
    public void addLevel(Level level) {
        this.levelMapper.insert(level);
    }

    @Override
    public void deleteLevel(Integer levelId) {
        this.levelMapper.deleteByPrimaryKey(levelId);
    }

    @Override
    public void updateLevel(Level level) {
        this.levelMapper.updateByPrimaryKeySelective(level);
    }

    @Override
    public List<Level> findAll() {
        return this.levelMapper.findAll();
    }

    @Override
    public Level findById(Integer levelId) {
        return this.levelMapper.selectByPrimaryKey(levelId);
    }
}
