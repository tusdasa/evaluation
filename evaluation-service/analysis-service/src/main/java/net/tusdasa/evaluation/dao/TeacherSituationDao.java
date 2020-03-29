package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.TeacherSituation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherSituationDao extends CrudRepository<TeacherSituation, Integer> {
    List<TeacherSituation> findAllByDepartmentId(Integer departmentId);

    List<TeacherSituation> findAllByDepartmentIdAndIdIn(Integer departmentId, List<Integer> ids);
}
