package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.Right;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RightDao extends MongoRepository<Right, Integer> {
}
