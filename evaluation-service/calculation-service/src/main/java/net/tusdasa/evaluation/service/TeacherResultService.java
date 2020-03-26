package net.tusdasa.evaluation.service;

import mathutils.MathUtils;
import mathutils.array.MathArrayUtils;
import net.tusdasa.evaluation.entity.*;
import org.springframework.stereotype.Service;

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

    private MathUtils mathUtils;

    public TeacherResultService(MathUtils mathUtils) {
        this.mathUtils = mathUtils;
    }

    public AcademicYearResult getTeacherResult(AcademicYearResult academicYearResult, TeacherSituation teacherSituation, AcademicYear academicYear) {
        if (academicYearResult == null) {
            return null;
        }
        // 获取教师评价
        List<FactorTeacher> factorTeacherList = teacherSituation.getFactorTeacherList();

        if (factorTeacherList != null && !factorTeacherList.isEmpty() && academicYearResult != null) {
            // 第一学期成绩
            TermResult startTermResult = academicYearResult.getFirstTerm();
            // 第二学期成绩
            TermResult endTermResult = academicYearResult.getSecondTerm();
            // 遍历
            for (FactorTeacher factorTeacher : factorTeacherList) {
                // 第一学期
                if (factorTeacher.getTermId().equals(startTermResult.getTermId())) {
                    // 取所有评价 计算
                    startTermResult.setTeacherScore(this.getMapScore(factorTeacher.getScoreList()));
                }
                // 第二学期
                if (factorTeacher.getTermId().equals(endTermResult.getTermId())) {
                    // 取所有评价 计算
                    endTermResult.setTeacherScore(this.getMapScore(factorTeacher.getScoreList()));
                }
            }
        }
        academicYearResult.setTeacherScore(mathUtils.meanNumber(new double[]{
                academicYearResult.getFirstTerm().getTeacherResult(),
                academicYearResult.getSecondTerm().getTeacherResult()
        }));
        academicYearResult.setStudentScore(mathUtils.meanNumber(new double[]{
                academicYearResult.getFirstTerm().getStudentResult(),
                academicYearResult.getSecondTerm().getStudentResult()
        }));
        academicYearResult.setTotal(
                academicYearResult.getTeacherScore() +
                        10 * academicYearResult.getStudentScore()
        );
        return academicYearResult;
    }


    private Integer getMapValue(Map<String, Integer> map) {
        Set<String> keySet = map.keySet();
        String[] keys = keySet.toArray(new String[1]);
        return map.get(keys[0]);
    }

    private double getMapScore(List<Map<String, Integer>> mapList) {
        // lambda表达式 转化为集合 getMapValue 取成绩
        List<Integer> allTeacherScore = mapList.stream()
                .map(this::getMapValue)
                .collect(Collectors.toList());
        // 返回成绩
        return mathUtils.meanNumber(MathArrayUtils.ListIntegerToArray(allTeacherScore));
    }

    /*
    // 此方法计算教学督导
    private double getTeacherScore(TeacherSituation teacherSituation, AcademicYear academicYear) {
        List<FactorTeacher> factorTeacherList = teacherSituation.getFactorTeacherList();
        // 第一学期
        Term start = academicYear.getStartTerm();
        // 第二学期
        Term end = academicYear.getEndTerm();
        double[] sum = new double[4];
        int i = 0;
        for (FactorTeacher factorTeacher : factorTeacherList) {

            if (factorTeacher.getTermId().equals(start.getTermId())){
                // 第一学期
                // 取所有评价
                List<Map<String, Integer>> mapList = factorTeacher.getScoreList();
                // 转化为数组
                List<Integer> allTeacher = mapList.stream().map(this::getMapValue).collect(Collectors.toList());
                // 转换为double[] 并求平均数
                sum[i++] = mathUtils.meanNumber(MathArrayUtils.ListIntegerToArray(allTeacher));
            }

            if (factorTeacher.getTermId().equals(end.getTermId())){
                // 第二学期
                // 取所有评价
                List<Map<String, Integer>> mapList = factorTeacher.getScoreList();
                // 转化为数组
                List<Integer> allTeacher = mapList.stream().map(this::getMapValue).collect(Collectors.toList());
                // 转换为double[] 并求平均数
                sum[i++] = mathUtils.meanNumber(MathArrayUtils.ListIntegerToArray(allTeacher));
            }

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

/*
    // 这个方法会计算学生成绩
    private double[] getStudentScore(StudentSituation studentSituation, AcademicYear academicYear) {
        Term startTerm = academicYear.getStartTerm();
        Term endTerm = academicYear.getEndTerm();
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
            if (factorCourse.getTermId().equals(startTerm.getTermId()) || factorCourse.getTermId().equals(endTerm.getTermId())) {
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
*/
