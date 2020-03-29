package net.tusdasa.evaluation.service;

import mathutils.MathUtils;
import mathutils.array.MathArrayUtils;
import mathutils.sorts.QuickSort;
import net.tusdasa.evaluation.entity.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: tusdasa
 * @Date: 2020-03-25 9:05 PM
 */

@Service
public class StudentResultService {

    private MathUtils mathUtils;

    private double PERCENTAGE = 0.1D;

    public StudentResultService(MathUtils mathUtils) {
        this.mathUtils = mathUtils;
    }


    public AcademicYearResult getStudentResult(StudentSituation studentSituation, AcademicYear academicYear) {

        // 第一学期
        Term start = academicYear.getStartTerm();
        // 第二学期
        Term end = academicYear.getEndTerm();
        // 第一学期成绩
        TermResult startTermResult = new TermResult(start);
        // 第二学期成绩
        TermResult endTermResult = new TermResult(end);

        // 获取所有的课程
        List<FactorCourse> factorCourseList = studentSituation.getFactorCourseList();
        if (factorCourseList != null && !factorCourseList.isEmpty()) {
            // 遍历课程
            for (FactorCourse factorCourse : factorCourseList) {

                // 第一学期 这门课的成绩
                if (factorCourse.getTermId().equals(start.getTermId())) {
                    // 班级成绩
                    double[] score = this.getAllClassScore(factorCourse.getClassesList());
                    startTermResult.setStudentScore(score);
                }

                // 第二学期
                if (factorCourse.getTermId().equals(end.getTermId())) {
                    // 班级成绩
                    double[] score = this.getAllClassScore(factorCourse.getClassesList());
                    endTermResult.setStudentScore(score);
                }
            }
            AcademicYearResult academicYearResult = this.getAcademicYearResult(studentSituation, academicYear);
            return this.setTermResult(academicYearResult, startTermResult, endTermResult);
        }
        return null;
    }

    /**
     * @param classesList 班级列表
     * @return result[0] 学生成绩
     * result[1] 标准差
     * result[2] 最小众数
     * result[3] 最大众数
     * result[4] 班级数
     * result[5] 总人数
     */
    private double[] getAllClassScore(List<FactorClasses> classesList) {
        // 存放结果
        double[] result = new double[]{0, 0, 0, 0, 0, 0};

        // 特殊情况处理
        if (classesList == null || classesList.isEmpty()) {
            return result;
        }

        // 每个班级的平均分
        double[] averageScore = new double[classesList.size()];
        // 无法确定每个班级成绩的个数，避免动态扩容使用LinkedList  确保线程安全
        List<Integer> All = Collections.synchronizedList(new LinkedList<>());
        int i = 0;
        // 遍历班级列表
        for (FactorClasses factorClasses : classesList) {
            // 取出班级所有学生的学生评价成绩 将当前班级的所有分数转换为数组
            double[] scoreList = MathArrayUtils.ListIntegerToArray(factorClasses.getTotalList());
            // 把当前所有分数增加到所有分数列表
            All.addAll(factorClasses.getTotalList());
            // 对当前班级的所有分数快速排序 快速排序 默认升序 无影响
            QuickSort.quickSort(scoreList, scoreList.length);
            // 去掉前后 PERCENTAGE % 的数
            double[] filterList = MathArrayUtils.getArrayFrontAndBack(scoreList, PERCENTAGE);
            // 计算评价分添加到班级评价分
            averageScore[i++] = mathUtils.meanNumber(filterList);
        }
        // 转换所有学生成绩到数组
        double[] res = MathArrayUtils.ListIntegerToArray(All);
        QuickSort.quickSort(res, res.length);
        // 计算所有学生成绩的众数
        double[] mode = mathUtils.modeNumber(res);
        // 计算完该门课程所有的班级平均分
        // 求所有班级的平均分的平均分
        result[0] = BigDecimal.valueOf(mathUtils.meanNumber(averageScore)).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        // 计算所有学生给出的评价中的标准差
        result[1] = BigDecimal.valueOf(mathUtils.standardDeviationNumber(res)).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        // 计算所有学生给出的评价中的众数 取第一个和最后一个
        if (mode.length == 1) {
            result[2] = mode[0];
            result[3] = mode[0];
        } else {
            result[2] = mode[0];
            result[3] = mode[mode.length - 1];
        }
        // 评价的班级总数
        result[4] = classesList.size();
        // 评价的总人数
        result[5] = All.size();
        // 聚集大量数据 清理
        //All.clear();
        return result;
    }

    private AcademicYearResult setTermResult(AcademicYearResult academicYearResult, TermResult startTermResult, TermResult endTermResult) {
        if (startTermResult != null) {
            academicYearResult.setFirstTerm(startTermResult);
        }
        if (endTermResult != null) {
            academicYearResult.setSecondTerm(endTermResult);
        }
        return academicYearResult;
    }

    // 装配学年信息
    private AcademicYearResult getAcademicYearResult(StudentSituation studentSituation, AcademicYear academicYear) {
        AcademicYearResult academicYearResult = new AcademicYearResult();
        academicYearResult.setId(studentSituation.getId());
        academicYearResult.setAcademicYearName(academicYear.getAcademicYearName());
        academicYearResult.setDepartmentName(studentSituation.getDepartmentName());
        academicYearResult.setProfessionalTitle(studentSituation.getProfessionalTitle());
        academicYearResult.setTeacherName(studentSituation.getTeacherName());
        return academicYearResult;
    }

}
