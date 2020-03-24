package net.tusdasa.evaluation.service;

import mathutils.MathUtils;
import mathutils.array.MathArrayUtils;
import mathutils.sorts.QuickSort;
import net.tusdasa.evaluation.client.AcademicYearClient;
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

    private AcademicYearClient academicYearClient;

    private double PERCENTAGE = 0.1D;

    public TeacherResultService(AnalysisClient analysisClient, MathUtils mathUtils, AcademicYearClient academicYearClient) {
        this.analysisClient = analysisClient;
        this.mathUtils = mathUtils;
        this.academicYearClient = academicYearClient;
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

    // 获得当前学年
    private AcademicYear getCurrentAcademicYear() {
        CommonResponse<AcademicYear> commonResponse = this.academicYearClient.current();
        if (commonResponse.success()) {
            return commonResponse.getData();
        }
        return null;
    }

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

    // 返回结果
    public Result getScore(Integer id) {
        StudentSituation studentSituation = this.getStudentSituation(id);
        TeacherSituation teacherSituation = this.getTeacherSituation(id);
        AcademicYear academicYear = this.getCurrentAcademicYear();

        if (studentSituation != null && teacherSituation != null && academicYear != null) {
            double[] studentScore = this.getStudentScore(studentSituation, academicYear);
            double teacherScore = this.getTeacherScore(teacherSituation, academicYear);
            return this.generateResult(studentSituation, studentScore, teacherScore, academicYear);
        }
        return null;
    }

    private Result generateResult(StudentSituation studentSituation, double[] studentScore, double teacherScore, AcademicYear academicYear) {
        Result result = new Result(studentScore, teacherScore);
        result.setAcademicYearName(academicYear.getAcademicYearName());
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

    // 此方法计算教学督导
    private double getTeacherScore(TeacherSituation teacherSituation, AcademicYear academicYear) {
        List<FactorTeacher> factorTeacherList = teacherSituation.getFactorTeacherList();
        Term startTerm = academicYear.getStartTerm();
        Term endTerm = academicYear.getEndTerm();
        double[] sum = new double[4];
        int i = 0;
        for (FactorTeacher factorTeacher : factorTeacherList) {
            if (factorTeacher.getTermId().equals(startTerm.getTermId()) || factorTeacher.getTermId().equals(endTerm.getTermId())) {
                // 取所有评价
                List<Map<String, Integer>> mapList = factorTeacher.getScoreList();
                // 转化为数组
                List<Integer> allTeacher = mapList.stream().map(this::getMapValue).collect(Collectors.toList());
                // 转换为double[] 并求和
                //sum[i++] = mathUtils.sumNumber(MathArrayUtils.ListIntegerToArray(allTeacher));
                sum[i++] = mathUtils.meanNumber(MathArrayUtils.ListIntegerToArray(allTeacher));
            }
        }

        return mathUtils.meanNumber(sum);

        /*
        // 取所有评价
        List<Map<String, Integer>> mapList =
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
         */
        // return doubles;
    }

    // 这个方法会计算学生成绩
    private double[] getStudentScore(StudentSituation studentSituation, AcademicYear academicYear) {
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
