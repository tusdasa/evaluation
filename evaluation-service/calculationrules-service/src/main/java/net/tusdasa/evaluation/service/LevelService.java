package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.Level;

import java.util.List;

public interface LevelService {

    void addLevel(Level level);

    void deleteLevel(Integer levelId);

    void updateLevel(Level level);

    List<Level> findAll();

    Level findById(Integer levelId);

}
