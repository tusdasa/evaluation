package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.AnalysisClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.FactorClasses;
import net.tusdasa.evaluation.entity.FactorCourse;
import net.tusdasa.evaluation.entity.StudentSituation;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 6:23 PM
 */

@Service
public class TeacherResultService {

    private AnalysisClient analysisClient;

    // 标准差
    private StandardDeviation standardDeviation;
    // 方差
    private Variance variance;

    public TeacherResultService(AnalysisClient analysisClient) {
        this.analysisClient = analysisClient;
        this.standardDeviation = new StandardDeviation();
        this.variance = new Variance();
    }

    private StudentSituation getTeacherSituation(Integer id) {
        CommonResponse<StudentSituation> situationCommonResponse = this.analysisClient.findStudentSituationById(id);
        if (situationCommonResponse.success()) {
            return situationCommonResponse.getData();
        }
        return null;
    }

    public Integer getScore(Integer id) {
        // 取教师信息
        StudentSituation studentSituation = this.getTeacherSituation(id);

        if (studentSituation != null) {
            // 取课程
            List<FactorCourse> factorCourseList = studentSituation.getFactorCourseList();
            for (FactorCourse factorCourse : factorCourseList) {
                // 课程班级
                List<FactorClasses> factorClassesList = factorCourse.getClassesList();
                for (FactorClasses factorClasses : factorClassesList) {
                    // 取出班级所有学生的成绩
                    Integer[] studentScore = factorClasses.getTotalList().toArray(new Integer[0]);
                    Arrays.sort(studentScore);
                    Integer n = (int) Math.floor(studentScore.length * 0.1);
                    Integer[] newS = Arrays.copyOfRange(studentScore, n, studentScore.length - 1 - n);
                    // 班级成绩
                    /*
                    Integer[] s1 = factorClasses.getTotalList().toArray(new Integer[0]);
                    double[] scores = new double[s1.length];
                    for (int i = 0; i < s1.length ; i++) {
                        scores[i] = Double.valueOf(s1[i]);
                    }
                    // 取出10%的人数
                    // double n = scores.length * 0.1;
                    System.out.println(Arrays.toString(scores));
                    // 方差
                    System.out.println(variance.evaluate(scores));
                    // 标准差
                    System.out.println(standardDeviation.evaluate(scores));
                    // 标准差
                    System.out.println(StatUtils.populationVariance(scores));
                     */
                }
            }
        } else {
            return 0;
        }
        return 1;
    }


}
