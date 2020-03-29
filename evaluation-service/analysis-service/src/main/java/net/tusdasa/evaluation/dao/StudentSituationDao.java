package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.StudentSituation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentSituationDao extends CrudRepository<StudentSituation, Integer> {
    List<StudentSituation> findAllByDepartmentId(Integer departmentId);
}
