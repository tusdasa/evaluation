/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50726
Source Host           : 127.0.0.1:3306
Source Database       : xynu_evaluation

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-03-11 10:14:25
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_academic_year
-- ----------------------------
DROP TABLE IF EXISTS `t_academic_year`;
CREATE TABLE `t_academic_year`
(
    `academic_year_id`   int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '学年ID',
    `academic_year_name` varchar(255)     DEFAULT NULL COMMENT '学年',
    `start_term`         int(11) unsigned DEFAULT NULL COMMENT '学年开始时间',
    `end_term`           int(11) unsigned DEFAULT NULL COMMENT '学年结束时间',
    PRIMARY KEY (`academic_year_id`),
    KEY `term_start` (`start_term`),
    KEY `term_end` (`end_term`),
    CONSTRAINT `term_end` FOREIGN KEY (`end_term`) REFERENCES `t_term` (`term_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `term_start` FOREIGN KEY (`start_term`) REFERENCES `t_term` (`term_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_academic_year
-- ----------------------------
INSERT INTO `t_academic_year`
VALUES ('1', '2019-2020学年', '1', '2');
INSERT INTO `t_academic_year`
VALUES ('2', '2018-2019学年', '4', '3');
INSERT INTO `t_academic_year`
VALUES ('3', '2020-2021学年', '5', '6');

-- ----------------------------
-- Table structure for t_calculation_rule
-- ----------------------------
DROP TABLE IF EXISTS `t_calculation_rule`;
CREATE TABLE `t_calculation_rule`
(
    `calculation_rule_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `unique_item`         int(1) unsigned  DEFAULT '1' COMMENT '分数只能一次或者是按次积累, 为1时是唯一项。为0是可以按次得分',
    `max_score`           int(11)          DEFAULT '-1' COMMENT '该项的最大分数，-1表示无限制',
    `min_score`           int(11)          DEFAULT '0' COMMENT '最低得分数，默认0',
    `level_type`          int(11) unsigned DEFAULT NULL COMMENT '是否按级别评分，需要时为评级ID',
    `evidence_type`       int(11) unsigned DEFAULT NULL COMMENT '是否需要举证 需要是为证据ID',
    PRIMARY KEY (`calculation_rule_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_calculation_rule
-- ----------------------------
INSERT INTO `t_calculation_rule`
VALUES ('1', '1', '5', '0', '1', null);

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`
(
    `course_id`       bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程ID',
    `course_name`     varchar(255)     DEFAULT NULL COMMENT '课程名',
    `teacher_work_id` int(11) unsigned DEFAULT NULL COMMENT '任课教师',
    `term_id`         int(11) unsigned DEFAULT NULL COMMENT '开课学期',
    PRIMARY KEY (`course_id`),
    KEY `course_term` (`term_id`),
    KEY `course_teacher` (`teacher_work_id`),
    CONSTRAINT `course_teacher` FOREIGN KEY (`teacher_work_id`) REFERENCES `t_teacher` (`work_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `course_term` FOREIGN KEY (`term_id`) REFERENCES `t_term` (`term_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course`
VALUES ('1', '高等数学', '1000000001', '1');
INSERT INTO `t_course`
VALUES ('2', '线性代数', '1000000007', '2');
INSERT INTO `t_course`
VALUES ('3', '概率论与数理统计', '1000000007', '2');
INSERT INTO `t_course`
VALUES ('4', '编译原理', '1000000003', '2');
INSERT INTO `t_course`
VALUES ('10', '高等数学', '1000000007', '2');
INSERT INTO `t_course`
VALUES ('11', '大学英语', '1000000004', '2');
INSERT INTO `t_course`
VALUES ('12', '离散数学', '1000000007', '4');
INSERT INTO `t_course`
VALUES ('13', '思想道德修养与法律基础', '1000000008', '2');
INSERT INTO `t_course`
VALUES ('14', '体育', '1000000005', '2');

-- ----------------------------
-- Table structure for t_course_class
-- ----------------------------
DROP TABLE IF EXISTS `t_course_class`;
CREATE TABLE `t_course_class`
(
    `course_id` bigint(20) unsigned DEFAULT NULL,
    `class_id`  int(10) unsigned    DEFAULT NULL,
    KEY `class_id` (`class_id`),
    KEY `course_id` (`course_id`),
    CONSTRAINT `class_id` FOREIGN KEY (`class_id`) REFERENCES `t_student_class` (`class_id`),
    CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_course_class
-- ----------------------------
INSERT INTO `t_course_class`
VALUES ('1', '2');
INSERT INTO `t_course_class`
VALUES ('2', '2');
INSERT INTO `t_course_class`
VALUES ('3', '2');
INSERT INTO `t_course_class`
VALUES ('1', '1');
INSERT INTO `t_course_class`
VALUES ('2', '1');
INSERT INTO `t_course_class`
VALUES ('3', '1');
INSERT INTO `t_course_class`
VALUES ('4', '1');
INSERT INTO `t_course_class`
VALUES ('10', '1');
INSERT INTO `t_course_class`
VALUES ('11', '1');
INSERT INTO `t_course_class`
VALUES ('12', '1');
INSERT INTO `t_course_class`
VALUES ('13', '1');
INSERT INTO `t_course_class`
VALUES ('14', '1');
INSERT INTO `t_course_class`
VALUES ('10', '2');
INSERT INTO `t_course_class`
VALUES ('11', '2');
INSERT INTO `t_course_class`
VALUES ('12', '2');
INSERT INTO `t_course_class`
VALUES ('13', '2');
INSERT INTO `t_course_class`
VALUES ('14', '2');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`
(
    `department_id`   int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门ID',
    `department_name` varchar(255) DEFAULT NULL COMMENT '部门名',
    PRIMARY KEY (`department_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department`
VALUES ('1', '文学与传播学院');
INSERT INTO `t_department`
VALUES ('2', '数学与信息科学学院');
INSERT INTO `t_department`
VALUES ('3', '物理与电子工程学院');
INSERT INTO `t_department`
VALUES ('4', '化学与化工学院');
INSERT INTO `t_department`
VALUES ('5', '资源环境与历史文化学院');
INSERT INTO `t_department`
VALUES ('6', '外国语学院');
INSERT INTO `t_department`
VALUES ('7', '体育学院');
INSERT INTO `t_department`
VALUES ('8', '马克思主义学院');
INSERT INTO `t_department`
VALUES ('9', '计算机学院');
INSERT INTO `t_department`
VALUES ('10', '美术学院');
INSERT INTO `t_department`
VALUES ('11', '于右任书法学院');
INSERT INTO `t_department`
VALUES ('12', '教育科学学院');
INSERT INTO `t_department`
VALUES ('13', '音乐学院');
INSERT INTO `t_department`
VALUES ('14', '设计学院');
INSERT INTO `t_department`
VALUES ('15', '经济与管理学院');
INSERT INTO `t_department`
VALUES ('16', '职业技术学院');
INSERT INTO `t_department`
VALUES ('17', '教务处');

-- ----------------------------
-- Table structure for t_evidence
-- ----------------------------
DROP TABLE IF EXISTS `t_evidence`;
CREATE TABLE `t_evidence`
(
    `evidence_id`    int(11) unsigned NOT NULL AUTO_INCREMENT,
    `evidence_name`  varchar(255) DEFAULT NULL,
    `evidence_type`  int(11)      DEFAULT NULL,
    `evidence_score` int(11)      DEFAULT NULL,
    PRIMARY KEY (`evidence_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_evidence
-- ----------------------------
INSERT INTO `t_evidence`
VALUES ('1', '听课记录', '1', '10');
INSERT INTO `t_evidence`
VALUES ('2', '会议记录', '2', '20');

-- ----------------------------
-- Table structure for t_first_kpi
-- ----------------------------
DROP TABLE IF EXISTS `t_first_kpi`;
CREATE TABLE `t_first_kpi`
(
    `first_kpi_id`     int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '第一指标ID',
    `firs_kpi_content` varchar(255)     DEFAULT NULL COMMENT '指标内容',
    `academic_year_id` int(11) unsigned DEFAULT NULL COMMENT '指标所在学年',
    PRIMARY KEY (`first_kpi_id`),
    KEY `academic_year` (`academic_year_id`),
    CONSTRAINT `academic_year` FOREIGN KEY (`academic_year_id`) REFERENCES `t_academic_year` (`academic_year_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_first_kpi
-- ----------------------------
INSERT INTO `t_first_kpi`
VALUES ('1', '教师完成教学工作的基本情况', '1');
INSERT INTO `t_first_kpi`
VALUES ('2', '教师参与学院教育教学建设贡献情况', '1');
INSERT INTO `t_first_kpi`
VALUES ('3', '教师参与学院教育教学改革和创新情况', '1');
INSERT INTO `t_first_kpi`
VALUES ('4', '督导评价', '1');
INSERT INTO `t_first_kpi`
VALUES ('5', '学生评价', '1');

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade`
(
    `grade_id`   int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '年级ID',
    `grade_name` varchar(255) DEFAULT NULL COMMENT '年级',
    PRIMARY KEY (`grade_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_grade
-- ----------------------------
INSERT INTO `t_grade`
VALUES ('1', '2019级');
INSERT INTO `t_grade`
VALUES ('2', '2018级');
INSERT INTO `t_grade`
VALUES ('3', '2017级');
INSERT INTO `t_grade`
VALUES ('4', '2016级');

-- ----------------------------
-- Table structure for t_level
-- ----------------------------
DROP TABLE IF EXISTS `t_level`;
CREATE TABLE `t_level`
(
    `level_id`   int(11) unsigned NOT NULL AUTO_INCREMENT,
    `level_name` varchar(255)     DEFAULT NULL,
    `level_type` int(11) unsigned DEFAULT NULL,
    `level_sore` int(11) unsigned DEFAULT NULL,
    PRIMARY KEY (`level_id`),
    KEY `level_type` (`level_type`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_level
-- ----------------------------
INSERT INTO `t_level`
VALUES ('1', '很好', '1', '6');
INSERT INTO `t_level`
VALUES ('2', '较好', '1', '4');
INSERT INTO `t_level`
VALUES ('3', '一般', '1', '2');
INSERT INTO `t_level`
VALUES ('4', '较差', '1', '0');
INSERT INTO `t_level`
VALUES ('5', '一等奖', '2', '100');
INSERT INTO `t_level`
VALUES ('6', '二等奖', '2', '60');
INSERT INTO `t_level`
VALUES ('7', '三等奖', '2', '40');

-- ----------------------------
-- Table structure for t_major
-- ----------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major`
(
    `major_id`   int(11) unsigned NOT NULL AUTO_INCREMENT,
    `major_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`major_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_major
-- ----------------------------
INSERT INTO `t_major`
VALUES ('1', '计算机科学与技术');

-- ----------------------------
-- Table structure for t_professional
-- ----------------------------
DROP TABLE IF EXISTS `t_professional`;
CREATE TABLE `t_professional`
(
    `professional_id`    int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '职称ID',
    `professional_title` varchar(255) DEFAULT NULL COMMENT '职称名',
    PRIMARY KEY (`professional_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_professional
-- ----------------------------
INSERT INTO `t_professional`
VALUES ('1', '教授');
INSERT INTO `t_professional`
VALUES ('2', '副教授');
INSERT INTO `t_professional`
VALUES ('3', '讲师');
INSERT INTO `t_professional`
VALUES ('4', '外聘');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`
(
    `role_id`   int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role`
VALUES ('1', '学生');
INSERT INTO `t_role`
VALUES ('2', '教师');
INSERT INTO `t_role`
VALUES ('3', '教学督导');
INSERT INTO `t_role`
VALUES ('4', '管理员');

-- ----------------------------
-- Table structure for t_second_kpi
-- ----------------------------
DROP TABLE IF EXISTS `t_second_kpi`;
CREATE TABLE `t_second_kpi`
(
    `second_kpi_id`      int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '第二指标ID',
    `first_kpi_id`       int(11) unsigned DEFAULT NULL COMMENT '所属第一指标',
    `second_kpi_content` varchar(255)     DEFAULT NULL COMMENT '指标内容',
    PRIMARY KEY (`second_kpi_id`),
    KEY `first_kpi_id` (`first_kpi_id`),
    CONSTRAINT `first_kpi_id` FOREIGN KEY (`first_kpi_id`) REFERENCES `t_first_kpi` (`first_kpi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 39
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_second_kpi
-- ----------------------------
INSERT INTO `t_second_kpi`
VALUES ('1', '1', '接受学院下达的理论课教学任务及完成情况');
INSERT INTO `t_second_kpi`
VALUES ('2', '1', '接受学院安排的实践教学等相关任务及完成情况');
INSERT INTO `t_second_kpi`
VALUES ('3', '1', '接受学院安排的毕业论文（设计）等相关工作任务及完成情况');
INSERT INTO `t_second_kpi`
VALUES ('4', '1', '接受学院安排的各项考试出题任务情况');
INSERT INTO `t_second_kpi`
VALUES ('5', '1', '接受学院监考等工作任务及完成情况');
INSERT INTO `t_second_kpi`
VALUES ('6', '1', '教师参与指导学生学科竞赛情况');
INSERT INTO `t_second_kpi`
VALUES ('7', '1', '教师参与教研活动情况');
INSERT INTO `t_second_kpi`
VALUES ('8', '2', '参与学院专业建设情况');
INSERT INTO `t_second_kpi`
VALUES ('9', '2', '参与学院课程建设情况');
INSERT INTO `t_second_kpi`
VALUES ('10', '2', '参与课程相关的实验室、实训项目或平台的建设');
INSERT INTO `t_second_kpi`
VALUES ('11', '2', '参与人才培养模式的改革情况');
INSERT INTO `t_second_kpi`
VALUES ('12', '2', '教学手段改革情况');
INSERT INTO `t_second_kpi`
VALUES ('13', '2', '教学方法革新情况');
INSERT INTO `t_second_kpi`
VALUES ('14', '2', '参与教学团队建设相关情况');
INSERT INTO `t_second_kpi`
VALUES ('15', '2', '参与人才培养方案修订情况');
INSERT INTO `t_second_kpi`
VALUES ('16', '2', '参与教学大纲修订情况');
INSERT INTO `t_second_kpi`
VALUES ('17', '3', '参与各类教改项目情况');
INSERT INTO `t_second_kpi`
VALUES ('18', '3', '参与教材建设情况');
INSERT INTO `t_second_kpi`
VALUES ('19', '3', '参与教学成果奖情况');
INSERT INTO `t_second_kpi`
VALUES ('20', '3', '教师参加各类教学竞赛情况');
INSERT INTO `t_second_kpi`
VALUES ('21', '3', '教师发表教育教学改革研究论文');
INSERT INTO `t_second_kpi`
VALUES ('22', '3', '科研成果转化为教学内容');
INSERT INTO `t_second_kpi`
VALUES ('23', '3', '科学研究优势转化为教学改革优势等');
INSERT INTO `t_second_kpi`
VALUES ('24', '3', '其他教育教学中的创新情况和突出成果情况');
INSERT INTO `t_second_kpi`
VALUES ('25', '4', '教师课堂教学准备及基本功情况');
INSERT INTO `t_second_kpi`
VALUES ('26', '4', '教师课堂教学实施情况');
INSERT INTO `t_second_kpi`
VALUES ('27', '4', '教师课堂教学方式手段的使用情况');
INSERT INTO `t_second_kpi`
VALUES ('28', '5', '教学热情');
INSERT INTO `t_second_kpi`
VALUES ('29', '5', '教学内容');
INSERT INTO `t_second_kpi`
VALUES ('30', '5', '教学组织');
INSERT INTO `t_second_kpi`
VALUES ('31', '5', '师生互动');
INSERT INTO `t_second_kpi`
VALUES ('32', '5', '师生关系');
INSERT INTO `t_second_kpi`
VALUES ('33', '5', '作业考核');
INSERT INTO `t_second_kpi`
VALUES ('34', '5', '教学效果');
INSERT INTO `t_second_kpi`
VALUES ('35', '4', '教师课堂教学准备及基本功情况 ');
INSERT INTO `t_second_kpi`
VALUES ('36', '4', '教师课堂教学实施情况');
INSERT INTO `t_second_kpi`
VALUES ('38', '4', '教师课堂教学方式手段的使用情况');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`
(
    `student_id`     bigint(20) unsigned NOT NULL COMMENT '学号',
    `student_secret` varchar(255)     DEFAULT NULL COMMENT '秘钥',
    `student_name`   varchar(255)     DEFAULT NULL COMMENT '学生姓名',
    `class_id`       int(11) unsigned DEFAULT NULL COMMENT '学生所属班级',
    PRIMARY KEY (`student_id`),
    KEY `student_class` (`class_id`),
    CONSTRAINT `student_class` FOREIGN KEY (`class_id`) REFERENCES `t_student_class` (`class_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student`
VALUES ('1810212128', '25d55ad283aa400af464c76d713c07ad', '鹿向南', '1');
INSERT INTO `t_student`
VALUES ('1810212129', '25d55ad283aa400af464c76d713c07ad', '狄书波', '1');

-- ----------------------------
-- Table structure for t_student_class
-- ----------------------------
DROP TABLE IF EXISTS `t_student_class`;
CREATE TABLE `t_student_class`
(
    `class_id`      int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '班级ID',
    `class_name`    varchar(255)     DEFAULT NULL COMMENT '班级名称',
    `department_id` int(11) unsigned DEFAULT NULL COMMENT '班级所属学院',
    `grade_id`      int(11) unsigned DEFAULT NULL,
    `major_id`      int(11) unsigned DEFAULT NULL,
    PRIMARY KEY (`class_id`),
    KEY `class_college` (`department_id`),
    KEY `class_grade` (`grade_id`),
    KEY `class_major` (`major_id`),
    CONSTRAINT `class_college` FOREIGN KEY (`department_id`) REFERENCES `t_department` (`department_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `class_grade` FOREIGN KEY (`grade_id`) REFERENCES `t_grade` (`grade_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `class_major` FOREIGN KEY (`major_id`) REFERENCES `t_major` (`major_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_student_class
-- ----------------------------
INSERT INTO `t_student_class`
VALUES ('1', '计科1801', '9', '1', '1');
INSERT INTO `t_student_class`
VALUES ('2', '计科1901', '9', '2', '1');

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher`
(
    `work_id`         int(11) unsigned NOT NULL COMMENT '工号',
    `teacher_secret`  varchar(255)     NOT NULL COMMENT '密码',
    `role_id`         int(11) unsigned NOT NULL DEFAULT '1' COMMENT '角色id',
    `department_id`   int(255) unsigned         DEFAULT NULL,
    `teacher_name`    varchar(255)              DEFAULT NULL,
    `professional_id` int(10) unsigned          DEFAULT NULL,
    `state_id`        int(11) unsigned          DEFAULT NULL COMMENT '教师状态  进修、访学、长期请假',
    PRIMARY KEY (`work_id`),
    KEY `role` (`role_id`),
    KEY `teacher_department` (`department_id`),
    KEY `teacher_professional` (`professional_id`),
    KEY `work_id` (`work_id`),
    KEY `teacher_state` (`state_id`),
    CONSTRAINT `teacher_department` FOREIGN KEY (`department_id`) REFERENCES `t_department` (`department_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `teacher_professional` FOREIGN KEY (`professional_id`) REFERENCES `t_professional` (`professional_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `teacher_role` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `teacher_state` FOREIGN KEY (`state_id`) REFERENCES `t_teacher_state` (`state_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户角色表';

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher`
VALUES ('1000000001', '25d55ad283aa400af464c76d713c07ad', '4', '9', '永宛阳', '1', '1');
INSERT INTO `t_teacher`
VALUES ('1000000002', '25d55ad283aa400af464c76d713c07ad', '3', '9', '磨安阳', '1', '1');
INSERT INTO `t_teacher`
VALUES ('1000000003', '25d55ad283aa400af464c76d713c07ad', '2', '9', '督寒绿', '1', '1');
INSERT INTO `t_teacher`
VALUES ('1000000004', '25d55ad283aa400af464c76d713c07ad', '2', '9', '夹谷巧兰', '2', '1');
INSERT INTO `t_teacher`
VALUES ('1000000005', '25d55ad283aa400af464c76d713c07ad', '2', '7', '不问萱', '3', '1');
INSERT INTO `t_teacher`
VALUES ('1000000006', '25d55ad283aa400af464c76d713c07ad', '2', '4', '濮阳醉珊', '4', '1');
INSERT INTO `t_teacher`
VALUES ('1000000007', '25d55ad283aa400af464c76d713c07ad', '2', '2', '乙雪珍', '2', '1');
INSERT INTO `t_teacher`
VALUES ('1000000008', '25d55ad283aa400af464c76d713c07ad', '2', '8', '硕旭昇', '4', '2');
INSERT INTO `t_teacher`
VALUES ('1000000009', '25d55ad283aa400af464c76d713c07ad', '2', '5', '百里雨欣', '4', '1');
INSERT INTO `t_teacher`
VALUES ('1000000010', '25d55ad283aa400af464c76d713c07ad', '2', '10', '抄兴云', '2', '1');

-- ----------------------------
-- Table structure for t_teacher_state
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher_state`;
CREATE TABLE `t_teacher_state`
(
    `state_id`   int(11) unsigned NOT NULL AUTO_INCREMENT,
    `state_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`state_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_teacher_state
-- ----------------------------
INSERT INTO `t_teacher_state`
VALUES ('1', '参与本次评教');
INSERT INTO `t_teacher_state`
VALUES ('2', '不参与本次评教');

-- ----------------------------
-- Table structure for t_term
-- ----------------------------
DROP TABLE IF EXISTS `t_term`;
CREATE TABLE `t_term`
(
    `term_id`    int(11) unsigned NOT NULL AUTO_INCREMENT,
    `start_time` datetime     DEFAULT NULL COMMENT '开始时间',
    `end_time`   datetime     DEFAULT NULL COMMENT '结束时间',
    `term_name`  varchar(255) DEFAULT NULL COMMENT '学期',
    PRIMARY KEY (`term_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_term
-- ----------------------------
INSERT INTO `t_term`
VALUES ('1', '2019-09-02 16:53:31', '2020-01-17 16:54:31', '2019-2020学年第一学期');
INSERT INTO `t_term`
VALUES ('2', '2020-02-01 16:55:03', '2020-07-10 16:55:11', '2019-2020学年第二学期');
INSERT INTO `t_term`
VALUES ('3', '2019-02-25 00:00:00', '2018-07-14 00:00:00', '2018-2019学年第二学期');
INSERT INTO `t_term`
VALUES ('4', '2019-09-02 00:00:00', '2020-01-17 00:00:00', '2019-2020学年第一学期');
INSERT INTO `t_term`
VALUES ('5', '2020-09-02 00:00:00', '2021-01-17 00:00:00', '2020-2021学年第一学期');
INSERT INTO `t_term`
VALUES ('6', '2021-02-04 00:00:00', '2021-07-14 00:00:00', '2020-2021学年第二学期');

-- ----------------------------
-- Table structure for t_third_kpi
-- ----------------------------
DROP TABLE IF EXISTS `t_third_kpi`;
CREATE TABLE `t_third_kpi`
(
    `third_kpi_id`        int(11) unsigned NOT NULL AUTO_INCREMENT,
    `second_kpi_id`       int(11) unsigned DEFAULT NULL,
    `third_kpi_content`   varchar(255)     DEFAULT NULL,
    `calculation_rule_id` int(11) unsigned DEFAULT NULL,
    PRIMARY KEY (`third_kpi_id`),
    KEY `calculation_rule` (`calculation_rule_id`),
    KEY `second_kpi` (`second_kpi_id`),
    CONSTRAINT `calculation_rule` FOREIGN KEY (`calculation_rule_id`) REFERENCES `t_calculation_rule` (`calculation_rule_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `second_kpi` FOREIGN KEY (`second_kpi_id`) REFERENCES `t_second_kpi` (`second_kpi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 35
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_third_kpi
-- ----------------------------
INSERT INTO `t_third_kpi`
VALUES ('1', '28', '老师对授课精心准备', '1');
INSERT INTO `t_third_kpi`
VALUES ('2', '28', '老师讲课时充满激情', '1');
INSERT INTO `t_third_kpi`
VALUES ('3', '28', '老师的授课风格很吸引人', '1');
INSERT INTO `t_third_kpi`
VALUES ('4', '29', '老师能阐述所讲概念的背景或来源', '1');
INSERT INTO `t_third_kpi`
VALUES ('5', '29', '老师对课程内容的讲授清楚易懂', '1');
INSERT INTO `t_third_kpi`
VALUES ('6', '29', '老师能将教学内容与实际应用相结合', '1');
INSERT INTO `t_third_kpi`
VALUES ('7', '29', '老师能充分介绍学科领域的最新进展', '1');
INSERT INTO `t_third_kpi`
VALUES ('8', '30', '老师每次课都有明确的教学目标', '1');
INSERT INTO `t_third_kpi`
VALUES ('9', '30', '教师能够营造有序的课堂环境，学生抬头率高', '1');
INSERT INTO `t_third_kpi`
VALUES ('10', '30', '老师有效地组织了课堂讨论，学生参与率高', '1');
INSERT INTO `t_third_kpi`
VALUES ('11', '31', '老师鼓励学生表达自己的观点', '1');
INSERT INTO `t_third_kpi`
VALUES ('12', '31', '老师鼓励学生提问并耐心解答', '1');
INSERT INTO `t_third_kpi`
VALUES ('13', '32', '老师尊重每个学生并以礼相待', '1');
INSERT INTO `t_third_kpi`
VALUES ('14', '32', '老师课内外都乐于帮助学生', '1');
INSERT INTO `t_third_kpi`
VALUES ('15', '33', '老师布置的作业有助于对课程的理解', '1');
INSERT INTO `t_third_kpi`
VALUES ('16', '33', '老师考核学生平时成绩的方法公平恰当', '1');
INSERT INTO `t_third_kpi`
VALUES ('17', '33', '老师对考核及作业的反馈很及时', '1');
INSERT INTO `t_third_kpi`
VALUES ('18', '34', '通过该课程的学习,我觉得很有收获，知识能力素质都有很大提升', '1');
INSERT INTO `t_third_kpi`
VALUES ('19', '34', '老师的教学激发了我学习该课程的兴趣', '1');
INSERT INTO `t_third_kpi`
VALUES ('20', '34', '我分析解决相关问题的能力提高了', '1');
INSERT INTO `t_third_kpi`
VALUES ('21', '35', '遵守教学纪律情况', '1');
INSERT INTO `t_third_kpi`
VALUES ('22', '35', '课前教学文案的准备情况', '1');
INSERT INTO `t_third_kpi`
VALUES ('23', '35', '教师语言表达与板书书写', '1');
INSERT INTO `t_third_kpi`
VALUES ('24', '36', '教学目标的设定', '1');
INSERT INTO `t_third_kpi`
VALUES ('25', '36', '教学设计和教学方法的选择', '1');
INSERT INTO `t_third_kpi`
VALUES ('26', '36', '教学内容的讲解方面', '1');
INSERT INTO `t_third_kpi`
VALUES ('27', '36', '组织课堂讨论情况', '1');
INSERT INTO `t_third_kpi`
VALUES ('28', '36', '课堂提供案例情况', '1');
INSERT INTO `t_third_kpi`
VALUES ('29', '36', '师生互动交流情况', '1');
INSERT INTO `t_third_kpi`
VALUES ('30', '36', '教学内容中学术前沿介绍', '1');
INSERT INTO `t_third_kpi`
VALUES ('31', '36', '课堂作业布置情况', '1');
INSERT INTO `t_third_kpi`
VALUES ('32', '38', '教学媒体辅助手段的选择', '1');
INSERT INTO `t_third_kpi`
VALUES ('33', '38', '信息化教学手段的运用', '1');
INSERT INTO `t_third_kpi`
VALUES ('34', '38', '混合式教学方法的使用情况', '1');
