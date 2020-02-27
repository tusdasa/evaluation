package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.StudentTeachingSituation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTeachingSituationDao extends CrudRepository<StudentTeachingSituation, Integer> {
}
