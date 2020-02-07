package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.Right;

import java.util.List;

public interface RightService {

    void addRight(Right right);

    void delRight(Integer id);

    void updateRight(Right right);

    List<Right> findAll();

    Right findById(Integer id);
}
