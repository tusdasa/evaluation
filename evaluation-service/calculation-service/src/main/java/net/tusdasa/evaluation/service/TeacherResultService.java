package net.tusdasa.evaluation.service;

import mathutils.MathUtils;
import mathutils.array.MathArrayUtils;
import mathutils.sorts.QuickSort;
import net.tusdasa.evaluation.client.AnalysisClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: tusdasa
 * @Date: 2020-03-20 6:23 PM
 */

@Service
public class TeacherResultService {

    private AnalysisClient analysisClient;

    private MathUtils mathUtils;

    private double PERCENTAGE = 0.1D;

    public TeacherResultService(AnalysisClient analysisClient, MathUtils mathUtils) {
        this.analysisClient = analysisClient;
        this.mathUtils = mathUtils;
    }

    // 获得学生评价结果
    private StudentSituation getStudentSituation(Integer id) {
        CommonResponse<StudentSituation> situationCommonResponse = this.analysisClient.findStudentSituationById(id);
        if (situationCommonResponse.success()) {
            return situationCommonResponse.getData();
        }
        return null;
    }

    // 获得教师评价结果
    private TeacherSituation getTeacherSituation(Integer id) {
        CommonResponse<TeacherSituation> situationCommonResponse = this.analysisClient.findTeacherSituation(id);
        if (situationCommonResponse.success()) {
            return situationCommonResponse.getData();
        }
        return null;
    }

    // 返回结果
    public Result getScore(Integer id) {
        /*
        // 取教师信息
        this.getTeacherScore(id);
        StudentSituation studentSituation = this.getStudentSituation(id);
        List<Integer> all = new LinkedList<>();
        if (studentSituation != null) {
            // 每门课程平均分 大小为课程列表大小
            double[] courseMean = new double[studentSituation.getFactorCourseList().size()];
            // 取课程
            List<FactorCourse> factorCourseList = studentSituation.getFactorCourseList();
            int j = 0;
            // 遍历课程
            for (FactorCourse factorCourse : factorCourseList) {
                // 取这一门课程上课的所有班级
                List<FactorClasses> factorClassesList = factorCourse.getClassesList();
                // 保存每个班级的平均分
                double[] classMean = new double[factorCourse.getClassesList().size()];
                int i = 0;
                for (FactorClasses factorClasses : factorClassesList) {
                    // 取出班级所有学生的学生评价成绩
                    double[] allStudentScore = MathArrayUtils.ListIntegerToArray(factorClasses.getTotalList());
                    all.addAll(factorClasses.getTotalList());
                    // 快速排序 默认升序 无影响
                    QuickSort.quickSort(allStudentScore, allStudentScore.length);
                    // 除去前后10%的成绩
                    double[] re_classMean = MathArrayUtils.getArrayFrontAndBack(allStudentScore, PERCENTAGE);
                    // 计算评价分添加到班级评价分
                    classMean[i++] = mathUtils.meanNumber(re_classMean);
                }
                // 计算完该门课程所有的班级平均分
                // 求所有班级的平均分的平均分
                courseMean[j++] = mathUtils.meanNumber(classMean);
            }
            // 计算每门课程平均分的平均分
            Result result = this.generateResult(studentSituation,
                    // 计算所有学生给出的评价中的众数 只取第一个
                    mathUtils.modeNumber(MathArrayUtils.ListIntegerToArray(all))[0],
                    // 计算所有学生给出的评价中的标准差
                    mathUtils.standardDeviationNumber(MathArrayUtils.ListIntegerToArray(all)),
                    // 去头去尾10% 的成绩的平均数
                    mathUtils.meanNumber(courseMean)
                    );
            System.out.println(result.toString());
            return result;
        } else {
            return null;
        }
        */
        StudentSituation studentSituation = this.getStudentSituation(id);
        TeacherSituation teacherSituation = this.getTeacherSituation(id);

        if (studentSituation != null && teacherSituation != null) {
            double[] studentScore = this.getStudentScore(studentSituation);
            double[] teacherScore = this.getTeacherScore(teacherSituation);
            return this.generateResult(studentSituation, studentScore, teacherScore);
        }
        return null;
    }

    private Result generateResult(StudentSituation studentSituation, double[] studentScore, double[] teacherScore) {
        Result result = new Result(studentScore, teacherScore);
        result.setAcademicYearName("111");
        result.setDepartmentName(studentSituation.getDepartmentName());
        result.setId(studentSituation.getId());
        result.setProfessionalTitle(studentSituation.getProfessionalTitle());
        result.setTeacherName(studentSituation.getTeacherName());
        return result;
    }

    private Integer getMapValue(Map<String, Integer> map) {
        Set<String> keySet = map.keySet();
        String[] keys = keySet.toArray(new String[1]);
        return map.get(keys[0]);
    }

    // 此方法计算
    private double[] getTeacherScore(TeacherSituation teacherSituation) {
        // 取所有评价
        List<Map<String, Integer>> mapList = teacherSituation.getScoreList();
        // 转化为数组
        List<Integer> allTeacher = mapList.stream().map(this::getMapValue).collect(Collectors.toList());
        // 转换为double[]
        double[] allTeacherScore = MathArrayUtils.ListIntegerToArray(allTeacher);

        double[] doubles = new double[3];
        // 计算所有教学督导给出的评价中的众数 只取第一个
        doubles[0] = mathUtils.modeNumber(allTeacherScore)[0];
        // 计算所有教学督导给出的评价中的标准差
        doubles[1] = mathUtils.standardDeviationNumber(allTeacherScore);
        // 平均数
        doubles[2] = mathUtils.meanNumber(allTeacherScore);
        return doubles;
    }

    // 这个方法会计算学生成绩
    private double[] getStudentScore(StudentSituation studentSituation) {
        int MEAN_SIZE = studentSituation.getFactorCourseList().size();
        int j = 0;
        // 保存所有学生的成绩
        List<Integer> All = new LinkedList<>();
        // 每门课程平均分 大小为课程列表大小
        double[] courseMean = new double[MEAN_SIZE];
        // 取课程
        List<FactorCourse> factorCourseList = studentSituation.getFactorCourseList();
        // 遍历课程
        for (FactorCourse factorCourse : factorCourseList) {
            // 取这一门课程上课的所有班级
            List<FactorClasses> factorClassesList = factorCourse.getClassesList();
            // 保存每个班级的平均分
            double[] classMean = new double[factorCourse.getClassesList().size()];
            int i = 0;
            for (FactorClasses factorClasses : factorClassesList) {
                // 取出班级所有学生的学生评价成绩
                double[] allStudentScore = MathArrayUtils.ListIntegerToArray(factorClasses.getTotalList());
                All.addAll(factorClasses.getTotalList());
                // 快速排序 默认升序 无影响
                QuickSort.quickSort(allStudentScore, allStudentScore.length);
                // 除去前后10%的成绩
                double[] re_classMean = MathArrayUtils.getArrayFrontAndBack(allStudentScore, PERCENTAGE);
                // 计算评价分添加到班级评价分
                classMean[i++] = mathUtils.meanNumber(re_classMean);
            }
            // 计算完该门课程所有的班级平均分
            // 求所有班级的平均分的平均分
            courseMean[j++] = mathUtils.meanNumber(classMean);
        }
        double[] doubles = new double[3];
        // 计算所有学生给出的评价中的众数 只取第一个
        doubles[0] = mathUtils.modeNumber(courseMean)[0];
        // 计算所有学生给出的评价中的标准差
        doubles[1] = mathUtils.standardDeviationNumber(MathArrayUtils.ListIntegerToArray(All));
        // 去头去尾10% 的成绩的平均数
        doubles[2] = mathUtils.meanNumber(courseMean);
        return doubles;
    }


}
