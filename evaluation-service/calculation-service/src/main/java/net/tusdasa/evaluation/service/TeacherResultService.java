package net.tusdasa.evaluation.service;

import mathutils.MathUtils;
import mathutils.array.MathArrayUtils;
import mathutils.sorts.QuickSort;
import net.tusdasa.evaluation.client.AnalysisClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.FactorClasses;
import net.tusdasa.evaluation.entity.FactorCourse;
import net.tusdasa.evaluation.entity.StudentSituation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 6:23 PM
 */

@Service
public class TeacherResultService {

    private AnalysisClient analysisClient;

    private MathUtils mathUtils;


    public TeacherResultService(AnalysisClient analysisClient, MathUtils mathUtils) {
        this.analysisClient = analysisClient;
        this.mathUtils = mathUtils;
    }

    private StudentSituation getTeacherSituation(Integer id) {
        CommonResponse<StudentSituation> situationCommonResponse = this.analysisClient.findStudentSituationById(id);
        if (situationCommonResponse.success()) {
            return situationCommonResponse.getData();
        }
        return null;
    }

    public Double getScore(Integer id) {
        // 取教师信息
        StudentSituation studentSituation = this.getTeacherSituation(id);

        if (studentSituation != null) {
            // 每门课程平均分
            double[] courseMean = new double[studentSituation.getFactorCourseList().size()];
            // 取课程
            List<FactorCourse> factorCourseList = studentSituation.getFactorCourseList();
            int j = 0;
            for (FactorCourse factorCourse : factorCourseList) {
                // 课程班级
                List<FactorClasses> factorClassesList = factorCourse.getClassesList();
                // 保存每个班级的平均分
                double[] classMean = new double[factorCourse.getClassesList().size()];
                int i = 0;
                for (FactorClasses factorClasses : factorClassesList) {
                    // 取出班级所有学生的学生评价成绩
                    double[] allStudentScore = MathArrayUtils.getDoubleArray(factorClasses.getTotalList());
                    // 快速排序
                    QuickSort.quickSort(allStudentScore, allStudentScore.length);
                    // 除去前后10%的成绩
                    double[] r1 = MathArrayUtils.getArrayFrontAndBack(allStudentScore, 0.1D);
                    classMean[i++] = mathUtils.meanNumber(r1);
                }

                // 读取完该门课程所有的班级平均分
                // 求所有班级的平均分
                courseMean[j++] = mathUtils.meanNumber(classMean);
            }
            return mathUtils.meanNumber(courseMean);
        } else {
            return 0.0;
        }
    }


}
