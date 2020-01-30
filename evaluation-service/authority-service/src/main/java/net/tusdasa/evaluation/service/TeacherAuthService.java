package net.tusdasa.evaluation.service;

import java.util.Map;

public interface TeacherAuthService {
    Map<String, Object> findTeacher(Integer workId, String password);
}
