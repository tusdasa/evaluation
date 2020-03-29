package net.tusdasa.evaluation.service;


import java.util.Map;

public interface StudentAuthService {
    Map<String, Object> findStudent(Long studentId, String password);
}
