package net.tusdasa.evaluation.service;

import java.util.List;

public interface BaseService<Model,PK> {
    List<Model> findAll();
    Model findById(PK id);
}
