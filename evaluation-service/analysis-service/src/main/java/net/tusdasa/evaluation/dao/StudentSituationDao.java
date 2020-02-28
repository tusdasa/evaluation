package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.StudentSituation;
import org.springframework.data.repository.CrudRepository;

public interface StudentSituationDao extends CrudRepository<StudentSituation, Integer> {
}
