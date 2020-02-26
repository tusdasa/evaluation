package net.tusdasa.evaluation.dao.teacher;

import net.tusdasa.evaluation.entity.TeacherEvaluation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author tusdasa
 * @version 1.0
 * @Description 教学督导评价结果储存
 */

public interface TeacherEvaluationDao extends MongoRepository<TeacherEvaluation, String> {

    /**
     * 查询某一教师某一学期的教学督导评价
     *
     * @param workId 被评价人工号（教师）
     * @param termId 当前学期Id
     */
    List<TeacherEvaluation> findAllByWorkIdAndTermId(Integer workId, Integer termId);

    /**
     * 查询某一教学督导的评价情况
     *
     * @param teacherId 教学督导工号
     */
    List<TeacherEvaluation> findAllByTeacherId(Integer teacherId);

    /**
     * 查询教学督导某一学期的评价情况
     *
     * @param teacherId 被评价人Id（教师）
     * @param termId    当前学期Id
     */
    List<TeacherEvaluation> findAllByTeacherIdAndTermId(Integer teacherId, Integer termId);

    /**
     * 用于分析服务异步计算成绩, 更新本次总成绩
     *
     *
     *
     */

}
