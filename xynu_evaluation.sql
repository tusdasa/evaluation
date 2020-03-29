/*
Source Server         : local
Source Server Version : 50726
Source Host           : 127.0.0.1:3306
Source Database       : xynu_evaluation

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-03-29 17:28:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_academic_year
-- ----------------------------
DROP TABLE IF EXISTS `t_academic_year`;
CREATE TABLE `t_academic_year` (
  `academic_year_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '学年ID',
  `academic_year_name` varchar(255) DEFAULT NULL COMMENT '学年',
  `start_term` int(11) unsigned DEFAULT NULL COMMENT '学年开始时间',
  `end_term` int(11) unsigned DEFAULT NULL COMMENT '学年结束时间',
  PRIMARY KEY (`academic_year_id`),
  KEY `term_start` (`start_term`),
  KEY `term_end` (`end_term`),
  CONSTRAINT `term_end` FOREIGN KEY (`end_term`) REFERENCES `t_term` (`term_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `term_start` FOREIGN KEY (`start_term`) REFERENCES `t_term` (`term_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_academic_year
-- ----------------------------
INSERT INTO `t_academic_year` VALUES ('1', '2019-2020学年', '1', '2');
INSERT INTO `t_academic_year` VALUES ('2', '2018-2019学年', '4', '3');
INSERT INTO `t_academic_year` VALUES ('3', '2020-2021学年', '5', '6');

-- ----------------------------
-- Table structure for t_calculation_rule
-- ----------------------------
DROP TABLE IF EXISTS `t_calculation_rule`;
CREATE TABLE `t_calculation_rule` (
  `calculation_rule_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `unique_item` int(1) unsigned DEFAULT '1' COMMENT '分数只能一次或者是按次积累, 为1时是唯一项。为0是可以按次得分',
  `max_score` int(11) DEFAULT '-1' COMMENT '该项的最大分数，-1表示无限制',
  `min_score` int(11) DEFAULT '0' COMMENT '最低得分数，默认0',
  `level_type` int(11) unsigned DEFAULT NULL COMMENT '是否按级别评分，需要时为评级ID',
  `evidence_type` int(11) unsigned DEFAULT NULL COMMENT '是否需要举证 需要是为证据ID',
  PRIMARY KEY (`calculation_rule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_calculation_rule
-- ----------------------------
INSERT INTO `t_calculation_rule` VALUES ('1', '1', '7', '0', '1', null);
INSERT INTO `t_calculation_rule` VALUES ('2', '0', '-1', '0', null, '1');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `course_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `course_name` varchar(255) DEFAULT NULL COMMENT '课程名',
  `teacher_work_id` int(11) unsigned DEFAULT NULL COMMENT '任课教师',
  `term_id` int(11) unsigned DEFAULT NULL COMMENT '开课学期',
  PRIMARY KEY (`course_id`),
  KEY `course_term` (`term_id`),
  KEY `course_teacher` (`teacher_work_id`),
  CONSTRAINT `course_teacher` FOREIGN KEY (`teacher_work_id`) REFERENCES `t_teacher` (`work_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `course_term` FOREIGN KEY (`term_id`) REFERENCES `t_term` (`term_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('1', '应用数学分析', '1000000020', '2');
INSERT INTO `t_course` VALUES ('2', '线性代数', '1000000021', '2');
INSERT INTO `t_course` VALUES ('3', '概率论与数理统计', '1000000007', '1');
INSERT INTO `t_course` VALUES ('4', '编译原理', '1000000003', '2');
INSERT INTO `t_course` VALUES ('10', '高等数学', '1000000007', '2');
INSERT INTO `t_course` VALUES ('11', '大学英语', '1000000059', '2');
INSERT INTO `t_course` VALUES ('12', '离散数学', '1000000022', '1');
INSERT INTO `t_course` VALUES ('13', '思想道德修养与法律基础', '1000000074', '2');
INSERT INTO `t_course` VALUES ('14', '体育', '1000000005', '1');
INSERT INTO `t_course` VALUES ('15', '计算机组成原理与系统结构', '1000000083', '2');
INSERT INTO `t_course` VALUES ('16', '毛泽东思想与中国特色社会主义理论体系', '1000000077', '1');
INSERT INTO `t_course` VALUES ('17', '微积分', '1000000007', '1');

-- ----------------------------
-- Table structure for t_course_class
-- ----------------------------
DROP TABLE IF EXISTS `t_course_class`;
CREATE TABLE `t_course_class` (
  `course_id` bigint(20) unsigned DEFAULT NULL,
  `class_id` int(10) unsigned DEFAULT NULL,
  KEY `class_id` (`class_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `class_id` FOREIGN KEY (`class_id`) REFERENCES `t_student_class` (`class_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course_class
-- ----------------------------
INSERT INTO `t_course_class` VALUES ('1', '1');
INSERT INTO `t_course_class` VALUES ('2', '1');
INSERT INTO `t_course_class` VALUES ('3', '1');
INSERT INTO `t_course_class` VALUES ('4', '1');
INSERT INTO `t_course_class` VALUES ('10', '1');
INSERT INTO `t_course_class` VALUES ('11', '1');
INSERT INTO `t_course_class` VALUES ('12', '1');
INSERT INTO `t_course_class` VALUES ('13', '1');
INSERT INTO `t_course_class` VALUES ('14', '1');
INSERT INTO `t_course_class` VALUES ('15', '1');
INSERT INTO `t_course_class` VALUES ('16', '1');
INSERT INTO `t_course_class` VALUES ('17', '1');
INSERT INTO `t_course_class` VALUES ('1', '2');
INSERT INTO `t_course_class` VALUES ('2', '2');
INSERT INTO `t_course_class` VALUES ('3', '2');
INSERT INTO `t_course_class` VALUES ('4', '2');
INSERT INTO `t_course_class` VALUES ('10', '2');
INSERT INTO `t_course_class` VALUES ('11', '2');
INSERT INTO `t_course_class` VALUES ('12', '2');
INSERT INTO `t_course_class` VALUES ('13', '2');
INSERT INTO `t_course_class` VALUES ('14', '2');
INSERT INTO `t_course_class` VALUES ('15', '2');
INSERT INTO `t_course_class` VALUES ('16', '2');
INSERT INTO `t_course_class` VALUES ('17', '2');
INSERT INTO `t_course_class` VALUES ('11', '3');
INSERT INTO `t_course_class` VALUES ('14', '3');
INSERT INTO `t_course_class` VALUES ('16', '3');
INSERT INTO `t_course_class` VALUES ('11', '4');
INSERT INTO `t_course_class` VALUES ('14', '4');
INSERT INTO `t_course_class` VALUES ('16', '4');
INSERT INTO `t_course_class` VALUES ('11', '5');
INSERT INTO `t_course_class` VALUES ('14', '5');
INSERT INTO `t_course_class` VALUES ('16', '5');
INSERT INTO `t_course_class` VALUES ('11', '6');
INSERT INTO `t_course_class` VALUES ('14', '6');
INSERT INTO `t_course_class` VALUES ('16', '6');
INSERT INTO `t_course_class` VALUES ('1', '6');
INSERT INTO `t_course_class` VALUES ('2', '6');
INSERT INTO `t_course_class` VALUES ('3', '6');
INSERT INTO `t_course_class` VALUES ('10', '6');
INSERT INTO `t_course_class` VALUES ('12', '6');
INSERT INTO `t_course_class` VALUES ('17', '6');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `department_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `department_name` varchar(255) DEFAULT NULL COMMENT '部门名',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('1', '文学与传播学院');
INSERT INTO `t_department` VALUES ('2', '数学与信息科学学院');
INSERT INTO `t_department` VALUES ('3', '物理与电子工程学院');
INSERT INTO `t_department` VALUES ('4', '化学与化工学院');
INSERT INTO `t_department` VALUES ('5', '资源环境与历史文化学院');
INSERT INTO `t_department` VALUES ('6', '外国语学院');
INSERT INTO `t_department` VALUES ('7', '体育学院');
INSERT INTO `t_department` VALUES ('8', '马克思主义学院');
INSERT INTO `t_department` VALUES ('9', '计算机学院');
INSERT INTO `t_department` VALUES ('10', '美术学院');
INSERT INTO `t_department` VALUES ('11', '于右任书法学院');
INSERT INTO `t_department` VALUES ('12', '教育科学学院');
INSERT INTO `t_department` VALUES ('13', '音乐学院');
INSERT INTO `t_department` VALUES ('14', '设计学院');
INSERT INTO `t_department` VALUES ('15', '经济与管理学院');
INSERT INTO `t_department` VALUES ('16', '职业技术学院');
INSERT INTO `t_department` VALUES ('17', '教务处');

-- ----------------------------
-- Table structure for t_evidence
-- ----------------------------
DROP TABLE IF EXISTS `t_evidence`;
CREATE TABLE `t_evidence` (
  `evidence_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `evidence_name` varchar(255) DEFAULT NULL,
  `evidence_type` int(11) DEFAULT NULL,
  `evidence_score` int(11) DEFAULT NULL,
  PRIMARY KEY (`evidence_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_evidence
-- ----------------------------
INSERT INTO `t_evidence` VALUES ('1', '听课记录', '1', '10');
INSERT INTO `t_evidence` VALUES ('2', '会议记录', '2', '20');

-- ----------------------------
-- Table structure for t_first_kpi
-- ----------------------------
DROP TABLE IF EXISTS `t_first_kpi`;
CREATE TABLE `t_first_kpi` (
  `first_kpi_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '第一指标ID',
  `firs_kpi_content` varchar(255) DEFAULT NULL COMMENT '指标内容',
  `academic_year_id` int(11) unsigned DEFAULT NULL COMMENT '指标所在学年',
  PRIMARY KEY (`first_kpi_id`),
  KEY `academic_year` (`academic_year_id`),
  CONSTRAINT `academic_year` FOREIGN KEY (`academic_year_id`) REFERENCES `t_academic_year` (`academic_year_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_first_kpi
-- ----------------------------
INSERT INTO `t_first_kpi` VALUES ('4', '督导评价', '1');
INSERT INTO `t_first_kpi` VALUES ('5', '学生评价', '1');

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade` (
  `grade_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '年级ID',
  `grade_name` varchar(255) DEFAULT NULL COMMENT '年级',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_grade
-- ----------------------------
INSERT INTO `t_grade` VALUES ('1', '2019级');
INSERT INTO `t_grade` VALUES ('2', '2018级');
INSERT INTO `t_grade` VALUES ('3', '2017级');
INSERT INTO `t_grade` VALUES ('4', '2016级');

-- ----------------------------
-- Table structure for t_level
-- ----------------------------
DROP TABLE IF EXISTS `t_level`;
CREATE TABLE `t_level` (
  `level_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `level_name` varchar(255) DEFAULT NULL,
  `level_type` int(11) unsigned DEFAULT NULL,
  `level_sore` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`level_id`),
  KEY `level_type` (`level_type`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_level
-- ----------------------------
INSERT INTO `t_level` VALUES ('1', '很好', '1', '6');
INSERT INTO `t_level` VALUES ('2', '较好', '1', '4');
INSERT INTO `t_level` VALUES ('3', '一般', '1', '2');
INSERT INTO `t_level` VALUES ('4', '较差', '1', '0');
INSERT INTO `t_level` VALUES ('5', '一等奖', '2', '100');
INSERT INTO `t_level` VALUES ('6', '二等奖', '2', '60');
INSERT INTO `t_level` VALUES ('7', '三等奖', '2', '40');

-- ----------------------------
-- Table structure for t_major
-- ----------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major` (
  `major_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `major_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_major
-- ----------------------------
INSERT INTO `t_major` VALUES ('1', '计算机科学与技术');
INSERT INTO `t_major` VALUES ('2', '汉语言文学');
INSERT INTO `t_major` VALUES ('3', '旅游管理');
INSERT INTO `t_major` VALUES ('4', '英语');
INSERT INTO `t_major` VALUES ('5', '统计学');
INSERT INTO `t_major` VALUES ('6', '体育教育');

-- ----------------------------
-- Table structure for t_professional
-- ----------------------------
DROP TABLE IF EXISTS `t_professional`;
CREATE TABLE `t_professional` (
  `professional_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '职称ID',
  `professional_title` varchar(255) DEFAULT NULL COMMENT '职称名',
  PRIMARY KEY (`professional_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_professional
-- ----------------------------
INSERT INTO `t_professional` VALUES ('1', '教授');
INSERT INTO `t_professional` VALUES ('2', '副教授');
INSERT INTO `t_professional` VALUES ('3', '讲师');
INSERT INTO `t_professional` VALUES ('4', '外聘');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '学生');
INSERT INTO `t_role` VALUES ('2', '教师');
INSERT INTO `t_role` VALUES ('3', '教学督导');
INSERT INTO `t_role` VALUES ('4', '管理员');

-- ----------------------------
-- Table structure for t_second_kpi
-- ----------------------------
DROP TABLE IF EXISTS `t_second_kpi`;
CREATE TABLE `t_second_kpi` (
  `second_kpi_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '第二指标ID',
  `first_kpi_id` int(11) unsigned DEFAULT NULL COMMENT '所属第一指标',
  `second_kpi_content` varchar(255) DEFAULT NULL COMMENT '指标内容',
  PRIMARY KEY (`second_kpi_id`),
  KEY `first_kpi_id` (`first_kpi_id`),
  CONSTRAINT `first_kpi_id` FOREIGN KEY (`first_kpi_id`) REFERENCES `t_first_kpi` (`first_kpi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_second_kpi
-- ----------------------------
INSERT INTO `t_second_kpi` VALUES ('24', '4', '教师课堂教学实施情况');
INSERT INTO `t_second_kpi` VALUES ('25', '4', '教师课堂教学准备及基本功情况');
INSERT INTO `t_second_kpi` VALUES ('27', '4', '教师课堂教学方式手段的使用情况');
INSERT INTO `t_second_kpi` VALUES ('28', '5', '教学热情');
INSERT INTO `t_second_kpi` VALUES ('29', '5', '教学内容');
INSERT INTO `t_second_kpi` VALUES ('30', '5', '教学组织');
INSERT INTO `t_second_kpi` VALUES ('31', '5', '师生互动');
INSERT INTO `t_second_kpi` VALUES ('32', '5', '师生关系');
INSERT INTO `t_second_kpi` VALUES ('33', '5', '作业考核');
INSERT INTO `t_second_kpi` VALUES ('34', '5', '教学效果');
INSERT INTO `t_second_kpi` VALUES ('35', '4', '教师课堂教学准备及基本功情况 ');
INSERT INTO `t_second_kpi` VALUES ('36', '4', '教师课堂教学实施情况');
INSERT INTO `t_second_kpi` VALUES ('38', '4', '教师课堂教学方式手段的使用情况');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `student_id` bigint(20) unsigned NOT NULL COMMENT '学号',
  `student_secret` varchar(255) DEFAULT NULL COMMENT '秘钥',
  `student_name` varchar(255) DEFAULT NULL COMMENT '学生姓名',
  `class_id` int(11) unsigned DEFAULT NULL COMMENT '学生所属班级',
  PRIMARY KEY (`student_id`),
  KEY `student_class` (`class_id`),
  CONSTRAINT `student_class` FOREIGN KEY (`class_id`) REFERENCES `t_student_class` (`class_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('1810212101', '25d55ad283aa400af464c76d713c07ad', '牛笑容', '1');
INSERT INTO `t_student` VALUES ('1810212102', '25d55ad283aa400af464c76d713c07ad', '阿笑萱', '1');
INSERT INTO `t_student` VALUES ('1810212103', '25d55ad283aa400af464c76d713c07ad', '太叔友易', '1');
INSERT INTO `t_student` VALUES ('1810212104', '25d55ad283aa400af464c76d713c07ad', '陶南珍', '1');
INSERT INTO `t_student` VALUES ('1810212105', '25d55ad283aa400af464c76d713c07ad', '逯冰双', '1');
INSERT INTO `t_student` VALUES ('1810212106', '25d55ad283aa400af464c76d713c07ad', '望醉霜', '1');
INSERT INTO `t_student` VALUES ('1810212107', '25d55ad283aa400af464c76d713c07ad', '房忆柔', '1');
INSERT INTO `t_student` VALUES ('1810212108', '25d55ad283aa400af464c76d713c07ad', '鲜雪卉', '1');
INSERT INTO `t_student` VALUES ('1810212109', '25d55ad283aa400af464c76d713c07ad', '求安萱', '1');
INSERT INTO `t_student` VALUES ('1810212110', '25d55ad283aa400af464c76d713c07ad', '仙飞南', '1');
INSERT INTO `t_student` VALUES ('1810212111', '25d55ad283aa400af464c76d713c07ad', '潮夜瑶', '1');
INSERT INTO `t_student` VALUES ('1810212112', '25d55ad283aa400af464c76d713c07ad', '镜迎夏', '1');
INSERT INTO `t_student` VALUES ('1810212113', '25d55ad283aa400af464c76d713c07ad', '游白香', '1');
INSERT INTO `t_student` VALUES ('1810212114', '25d55ad283aa400af464c76d713c07ad', '茅绿蝶', '1');
INSERT INTO `t_student` VALUES ('1810212115', '25d55ad283aa400af464c76d713c07ad', '吉乐岚', '1');
INSERT INTO `t_student` VALUES ('1810212116', '25d55ad283aa400af464c76d713c07ad', '斐书雪', '1');
INSERT INTO `t_student` VALUES ('1810212117', '25d55ad283aa400af464c76d713c07ad', '林冷琴', '1');
INSERT INTO `t_student` VALUES ('1810212118', '25d55ad283aa400af464c76d713c07ad', '安又容', '1');
INSERT INTO `t_student` VALUES ('1810212119', '25d55ad283aa400af464c76d713c07ad', '子车尔真', '1');
INSERT INTO `t_student` VALUES ('1810212120', '25d55ad283aa400af464c76d713c07ad', '泉忆筠', '1');
INSERT INTO `t_student` VALUES ('1810212121', '25d55ad283aa400af464c76d713c07ad', '丘盼易', '1');
INSERT INTO `t_student` VALUES ('1810212122', '25d55ad283aa400af464c76d713c07ad', '谬宛竹', '1');
INSERT INTO `t_student` VALUES ('1810212123', '25d55ad283aa400af464c76d713c07ad', '空从柳', '1');
INSERT INTO `t_student` VALUES ('1810212124', '25d55ad283aa400af464c76d713c07ad', '弓香柏', '1');
INSERT INTO `t_student` VALUES ('1810212125', '25d55ad283aa400af464c76d713c07ad', '延春香', '1');
INSERT INTO `t_student` VALUES ('1810212126', '25d55ad283aa400af464c76d713c07ad', '考宛云', '1');
INSERT INTO `t_student` VALUES ('1810212127', '25d55ad283aa400af464c76d713c07ad', '行香菱', '1');
INSERT INTO `t_student` VALUES ('1810212128', '25d55ad283aa400af464c76d713c07ad', '鹿向南', '2');
INSERT INTO `t_student` VALUES ('1810212129', '25d55ad283aa400af464c76d713c07ad', '狄书波', '1');
INSERT INTO `t_student` VALUES ('1810212130', '25d55ad283aa400af464c76d713c07ad', '羊舌雅蓉', '1');
INSERT INTO `t_student` VALUES ('1810212131', '25d55ad283aa400af464c76d713c07ad', '畅笑柳', '1');
INSERT INTO `t_student` VALUES ('1810212132', '25d55ad283aa400af464c76d713c07ad', '牟小凝', '1');
INSERT INTO `t_student` VALUES ('1810212133', '25d55ad283aa400af464c76d713c07ad', '菅昭', '1');
INSERT INTO `t_student` VALUES ('1810212134', '25d55ad283aa400af464c76d713c07ad', '靖又曼', '1');
INSERT INTO `t_student` VALUES ('1810212135', '25d55ad283aa400af464c76d713c07ad', '程寄容', '1');
INSERT INTO `t_student` VALUES ('1810212136', '25d55ad283aa400af464c76d713c07ad', '恽千凡', '1');
INSERT INTO `t_student` VALUES ('1810212137', '25d55ad283aa400af464c76d713c07ad', '时新筠', '2');
INSERT INTO `t_student` VALUES ('1810212138', '25d55ad283aa400af464c76d713c07ad', '左冷天', '2');
INSERT INTO `t_student` VALUES ('1810212139', '25d55ad283aa400af464c76d713c07ad', '芒半槐', '2');
INSERT INTO `t_student` VALUES ('1810212140', '25d55ad283aa400af464c76d713c07ad', '续沛亦', '2');
INSERT INTO `t_student` VALUES ('1810212141', '25d55ad283aa400af464c76d713c07ad', '良映云', '2');
INSERT INTO `t_student` VALUES ('1810212142', '25d55ad283aa400af464c76d713c07ad', '智寄蕾', '2');
INSERT INTO `t_student` VALUES ('1810212143', '25d55ad283aa400af464c76d713c07ad', '红从霜', '2');
INSERT INTO `t_student` VALUES ('1810212144', '25d55ad283aa400af464c76d713c07ad', '岳寄柔', '2');
INSERT INTO `t_student` VALUES ('1810212145', '25d55ad283aa400af464c76d713c07ad', '蹇醉容', '2');
INSERT INTO `t_student` VALUES ('1810212146', '25d55ad283aa400af464c76d713c07ad', '康又夏', '2');
INSERT INTO `t_student` VALUES ('1810212147', '25d55ad283aa400af464c76d713c07ad', '韶乙', '2');
INSERT INTO `t_student` VALUES ('1810212148', '25d55ad283aa400af464c76d713c07ad', '依绿柳', '2');
INSERT INTO `t_student` VALUES ('1810212149', '25d55ad283aa400af464c76d713c07ad', '说冰琴', '2');
INSERT INTO `t_student` VALUES ('1810212150', '25d55ad283aa400af464c76d713c07ad', '冠芷烟', '2');
INSERT INTO `t_student` VALUES ('1810212151', '25d55ad283aa400af464c76d713c07ad', '司马念霜', '2');
INSERT INTO `t_student` VALUES ('1810212152', '25d55ad283aa400af464c76d713c07ad', '扶若南', '2');
INSERT INTO `t_student` VALUES ('1810212153', '25d55ad283aa400af464c76d713c07ad', '颜沛凝', '2');
INSERT INTO `t_student` VALUES ('1810212154', '25d55ad283aa400af464c76d713c07ad', '边慕丹', '2');
INSERT INTO `t_student` VALUES ('1810212155', '25d55ad283aa400af464c76d713c07ad', '裴碧竹', '2');
INSERT INTO `t_student` VALUES ('1810212156', '25d55ad283aa400af464c76d713c07ad', '南宫凌文', '2');
INSERT INTO `t_student` VALUES ('1810212157', '25d55ad283aa400af464c76d713c07ad', '梅代桃', '2');
INSERT INTO `t_student` VALUES ('1810212158', '25d55ad283aa400af464c76d713c07ad', '朱沛凝', '2');
INSERT INTO `t_student` VALUES ('1810212159', '25d55ad283aa400af464c76d713c07ad', '塞绿蕊', '2');
INSERT INTO `t_student` VALUES ('1810212160', '25d55ad283aa400af464c76d713c07ad', '法芷梦', '2');
INSERT INTO `t_student` VALUES ('1810212161', '25d55ad283aa400af464c76d713c07ad', '建新之', '2');
INSERT INTO `t_student` VALUES ('1810212162', '25d55ad283aa400af464c76d713c07ad', '箕冷露', '2');
INSERT INTO `t_student` VALUES ('1810212163', '25d55ad283aa400af464c76d713c07ad', '绍曼冬', '2');
INSERT INTO `t_student` VALUES ('1810212164', '25d55ad283aa400af464c76d713c07ad', '单珍', '2');
INSERT INTO `t_student` VALUES ('1810212165', '25d55ad283aa400af464c76d713c07ad', '郯如凡', '2');
INSERT INTO `t_student` VALUES ('1810212166', '25d55ad283aa400af464c76d713c07ad', '乔平萱', '2');
INSERT INTO `t_student` VALUES ('1810212167', '25d55ad283aa400af464c76d713c07ad', '昝天容', '2');
INSERT INTO `t_student` VALUES ('1810212168', '25d55ad283aa400af464c76d713c07ad', '端山天', '2');
INSERT INTO `t_student` VALUES ('1810212169', '25d55ad283aa400af464c76d713c07ad', '瑞醉双', '2');
INSERT INTO `t_student` VALUES ('1810212170', '25d55ad283aa400af464c76d713c07ad', '云幻丝', '2');
INSERT INTO `t_student` VALUES ('1810212171', '25d55ad283aa400af464c76d713c07ad', '蒋沛槐', '2');
INSERT INTO `t_student` VALUES ('1810212172', '25d55ad283aa400af464c76d713c07ad', '翠醉冬', '3');
INSERT INTO `t_student` VALUES ('1810212173', '25d55ad283aa400af464c76d713c07ad', '其紫玉', '3');
INSERT INTO `t_student` VALUES ('1810212174', '25d55ad283aa400af464c76d713c07ad', '后依玉', '3');
INSERT INTO `t_student` VALUES ('1810212175', '25d55ad283aa400af464c76d713c07ad', '郗傲易', '3');
INSERT INTO `t_student` VALUES ('1810212176', '25d55ad283aa400af464c76d713c07ad', '冀涵真', '3');
INSERT INTO `t_student` VALUES ('1810212177', '25d55ad283aa400af464c76d713c07ad', '夙怜雁', '3');
INSERT INTO `t_student` VALUES ('1810212178', '25d55ad283aa400af464c76d713c07ad', '郝盼旋', '3');
INSERT INTO `t_student` VALUES ('1810212179', '25d55ad283aa400af464c76d713c07ad', '眭巳', '3');
INSERT INTO `t_student` VALUES ('1810212180', '25d55ad283aa400af464c76d713c07ad', '受听容', '3');
INSERT INTO `t_student` VALUES ('1810212181', '25d55ad283aa400af464c76d713c07ad', '崇冬烟', '3');
INSERT INTO `t_student` VALUES ('1810212182', '25d55ad283aa400af464c76d713c07ad', '戎婵', '3');
INSERT INTO `t_student` VALUES ('1810212183', '25d55ad283aa400af464c76d713c07ad', '洪如香', '3');
INSERT INTO `t_student` VALUES ('1810212184', '25d55ad283aa400af464c76d713c07ad', '老笑卉', '3');
INSERT INTO `t_student` VALUES ('1810212185', '25d55ad283aa400af464c76d713c07ad', '施幼旋', '3');
INSERT INTO `t_student` VALUES ('1810212186', '25d55ad283aa400af464c76d713c07ad', '穰香岚', '3');
INSERT INTO `t_student` VALUES ('1810212187', '25d55ad283aa400af464c76d713c07ad', '钊乐琴', '3');
INSERT INTO `t_student` VALUES ('1810212188', '25d55ad283aa400af464c76d713c07ad', '耿安夏', '3');
INSERT INTO `t_student` VALUES ('1810212189', '25d55ad283aa400af464c76d713c07ad', '蔚问寒', '3');
INSERT INTO `t_student` VALUES ('1810212190', '25d55ad283aa400af464c76d713c07ad', '台之南', '3');
INSERT INTO `t_student` VALUES ('1810212191', '25d55ad283aa400af464c76d713c07ad', '呼靖梅', '3');
INSERT INTO `t_student` VALUES ('1810212192', '25d55ad283aa400af464c76d713c07ad', '税寻巧', '3');
INSERT INTO `t_student` VALUES ('1810212193', '25d55ad283aa400af464c76d713c07ad', '郸夏雪', '3');
INSERT INTO `t_student` VALUES ('1810212194', '25d55ad283aa400af464c76d713c07ad', '袁梦秋', '3');
INSERT INTO `t_student` VALUES ('1810212195', '25d55ad283aa400af464c76d713c07ad', '焦寒蕊', '3');
INSERT INTO `t_student` VALUES ('1810212196', '25d55ad283aa400af464c76d713c07ad', '佛初霜', '4');
INSERT INTO `t_student` VALUES ('1810212197', '25d55ad283aa400af464c76d713c07ad', '少以阳', '4');
INSERT INTO `t_student` VALUES ('1810212198', '25d55ad283aa400af464c76d713c07ad', '杭如珍', '4');
INSERT INTO `t_student` VALUES ('1810212199', '25d55ad283aa400af464c76d713c07ad', '祖秋玉', '4');
INSERT INTO `t_student` VALUES ('1810212200', '25d55ad283aa400af464c76d713c07ad', '裘采冬', '4');
INSERT INTO `t_student` VALUES ('1810212201', '25d55ad283aa400af464c76d713c07ad', '杜语蓉', '4');
INSERT INTO `t_student` VALUES ('1810212202', '25d55ad283aa400af464c76d713c07ad', '朋怜雪', '4');
INSERT INTO `t_student` VALUES ('1810212203', '25d55ad283aa400af464c76d713c07ad', '檀雨', '4');
INSERT INTO `t_student` VALUES ('1810212204', '25d55ad283aa400af464c76d713c07ad', '家念槐', '4');
INSERT INTO `t_student` VALUES ('1810212205', '25d55ad283aa400af464c76d713c07ad', '荀涒滩', '4');
INSERT INTO `t_student` VALUES ('1810212206', '25d55ad283aa400af464c76d713c07ad', '石雁翠', '4');
INSERT INTO `t_student` VALUES ('1810212207', '25d55ad283aa400af464c76d713c07ad', '道若香', '4');
INSERT INTO `t_student` VALUES ('1810212208', '25d55ad283aa400af464c76d713c07ad', '楚含槐', '4');
INSERT INTO `t_student` VALUES ('1810212209', '25d55ad283aa400af464c76d713c07ad', '塔香阳', '4');
INSERT INTO `t_student` VALUES ('1810212210', '25d55ad283aa400af464c76d713c07ad', '贾书文', '4');
INSERT INTO `t_student` VALUES ('1810212211', '25d55ad283aa400af464c76d713c07ad', '都痴梦', '4');
INSERT INTO `t_student` VALUES ('1810212212', '25d55ad283aa400af464c76d713c07ad', '卷依竹', '4');
INSERT INTO `t_student` VALUES ('1810212213', '25d55ad283aa400af464c76d713c07ad', '阳巧青', '4');
INSERT INTO `t_student` VALUES ('1810212214', '25d55ad283aa400af464c76d713c07ad', '翟山梅', '4');
INSERT INTO `t_student` VALUES ('1810212215', '25d55ad283aa400af464c76d713c07ad', '黄之彤', '4');
INSERT INTO `t_student` VALUES ('1810212216', '25d55ad283aa400af464c76d713c07ad', '隽以文', '4');
INSERT INTO `t_student` VALUES ('1810212217', '25d55ad283aa400af464c76d713c07ad', '千又琴', '4');
INSERT INTO `t_student` VALUES ('1810212218', '25d55ad283aa400af464c76d713c07ad', '来忆风', '4');
INSERT INTO `t_student` VALUES ('1810212219', '25d55ad283aa400af464c76d713c07ad', '陆靖蕊', '4');
INSERT INTO `t_student` VALUES ('1810212220', '25d55ad283aa400af464c76d713c07ad', '函谷香', '4');
INSERT INTO `t_student` VALUES ('1810212221', '25d55ad283aa400af464c76d713c07ad', '庄醉芙', '4');
INSERT INTO `t_student` VALUES ('1810212222', '25d55ad283aa400af464c76d713c07ad', '由初筠', '5');
INSERT INTO `t_student` VALUES ('1810212223', '25d55ad283aa400af464c76d713c07ad', '那拉香之', '5');
INSERT INTO `t_student` VALUES ('1810212224', '25d55ad283aa400af464c76d713c07ad', '那半芙', '5');
INSERT INTO `t_student` VALUES ('1810212225', '25d55ad283aa400af464c76d713c07ad', '理迎彤', '5');
INSERT INTO `t_student` VALUES ('1810212226', '25d55ad283aa400af464c76d713c07ad', '生思凡', '5');
INSERT INTO `t_student` VALUES ('1810212227', '25d55ad283aa400af464c76d713c07ad', '全千凡', '5');
INSERT INTO `t_student` VALUES ('1810212228', '25d55ad283aa400af464c76d713c07ad', '多白翠', '5');
INSERT INTO `t_student` VALUES ('1810212229', '25d55ad283aa400af464c76d713c07ad', '公孙向凝', '5');
INSERT INTO `t_student` VALUES ('1810212230', '25d55ad283aa400af464c76d713c07ad', '孝千亦', '5');
INSERT INTO `t_student` VALUES ('1810212231', '25d55ad283aa400af464c76d713c07ad', '辜千白', '5');
INSERT INTO `t_student` VALUES ('1810212232', '25d55ad283aa400af464c76d713c07ad', '咸易云', '5');
INSERT INTO `t_student` VALUES ('1810212233', '25d55ad283aa400af464c76d713c07ad', '华书竹', '5');
INSERT INTO `t_student` VALUES ('1810212234', '25d55ad283aa400af464c76d713c07ad', '寻痴梅', '5');
INSERT INTO `t_student` VALUES ('1810212235', '25d55ad283aa400af464c76d713c07ad', '司空思萱', '5');
INSERT INTO `t_student` VALUES ('1810212236', '25d55ad283aa400af464c76d713c07ad', '军尔风', '5');
INSERT INTO `t_student` VALUES ('1810212237', '25d55ad283aa400af464c76d713c07ad', '独寻露', '5');
INSERT INTO `t_student` VALUES ('1810212238', '25d55ad283aa400af464c76d713c07ad', '皇甫又蓉', '5');
INSERT INTO `t_student` VALUES ('1810212239', '25d55ad283aa400af464c76d713c07ad', '禹含阳', '5');
INSERT INTO `t_student` VALUES ('1810212240', '25d55ad283aa400af464c76d713c07ad', '敖易梦', '5');
INSERT INTO `t_student` VALUES ('1810212241', '25d55ad283aa400af464c76d713c07ad', '廖香绿', '5');
INSERT INTO `t_student` VALUES ('1810212242', '25d55ad283aa400af464c76d713c07ad', '钟傲双', '5');
INSERT INTO `t_student` VALUES ('1810212243', '25d55ad283aa400af464c76d713c07ad', '饶白萱', '5');
INSERT INTO `t_student` VALUES ('1810212244', '25d55ad283aa400af464c76d713c07ad', '充含岚', '5');
INSERT INTO `t_student` VALUES ('1810212245', '25d55ad283aa400af464c76d713c07ad', '邸山寒', '5');
INSERT INTO `t_student` VALUES ('1810212246', '25d55ad283aa400af464c76d713c07ad', '昌白凝', '5');
INSERT INTO `t_student` VALUES ('1810212247', '25d55ad283aa400af464c76d713c07ad', '单于午', '5');
INSERT INTO `t_student` VALUES ('1810212248', '25d55ad283aa400af464c76d713c07ad', '六白萱', '5');
INSERT INTO `t_student` VALUES ('1810212249', '25d55ad283aa400af464c76d713c07ad', '狄念梦', '5');
INSERT INTO `t_student` VALUES ('1810212250', '25d55ad283aa400af464c76d713c07ad', '谢又绿', '5');
INSERT INTO `t_student` VALUES ('1810212251', '25d55ad283aa400af464c76d713c07ad', '郑凌云', '5');
INSERT INTO `t_student` VALUES ('1810212252', '25d55ad283aa400af464c76d713c07ad', '尤秋瑶', '5');
INSERT INTO `t_student` VALUES ('1810212253', '25d55ad283aa400af464c76d713c07ad', '战梦玉', '6');
INSERT INTO `t_student` VALUES ('1810212254', '25d55ad283aa400af464c76d713c07ad', '广雪珊', '6');
INSERT INTO `t_student` VALUES ('1810212255', '25d55ad283aa400af464c76d713c07ad', '俞雪真', '6');
INSERT INTO `t_student` VALUES ('1810212256', '25d55ad283aa400af464c76d713c07ad', '苗盼松', '6');
INSERT INTO `t_student` VALUES ('1810212257', '25d55ad283aa400af464c76d713c07ad', '淦寻阳', '6');
INSERT INTO `t_student` VALUES ('1810212258', '25d55ad283aa400af464c76d713c07ad', '涂代秋', '6');
INSERT INTO `t_student` VALUES ('1810212259', '25d55ad283aa400af464c76d713c07ad', '摩天烟', '6');
INSERT INTO `t_student` VALUES ('1810212260', '25d55ad283aa400af464c76d713c07ad', '剑琴', '6');
INSERT INTO `t_student` VALUES ('1810212261', '25d55ad283aa400af464c76d713c07ad', '骑笑晴', '6');
INSERT INTO `t_student` VALUES ('1810212262', '25d55ad283aa400af464c76d713c07ad', '第五平卉', '6');
INSERT INTO `t_student` VALUES ('1810212263', '25d55ad283aa400af464c76d713c07ad', '锁巧易', '6');
INSERT INTO `t_student` VALUES ('1810212264', '25d55ad283aa400af464c76d713c07ad', '接以冬', '6');
INSERT INTO `t_student` VALUES ('1810212265', '25d55ad283aa400af464c76d713c07ad', '支怀寒', '6');
INSERT INTO `t_student` VALUES ('1810212266', '25d55ad283aa400af464c76d713c07ad', '宦初阳', '6');
INSERT INTO `t_student` VALUES ('1810212267', '25d55ad283aa400af464c76d713c07ad', '呼延新兰', '6');
INSERT INTO `t_student` VALUES ('1810212268', '25d55ad283aa400af464c76d713c07ad', '苏罗敷', '6');
INSERT INTO `t_student` VALUES ('1810212269', '25d55ad283aa400af464c76d713c07ad', '辟傲旋', '6');
INSERT INTO `t_student` VALUES ('1810212270', '25d55ad283aa400af464c76d713c07ad', '势初珍', '6');
INSERT INTO `t_student` VALUES ('1810212271', '25d55ad283aa400af464c76d713c07ad', '皇幼柳', '6');
INSERT INTO `t_student` VALUES ('1810212272', '25d55ad283aa400af464c76d713c07ad', '袭向菱', '6');
INSERT INTO `t_student` VALUES ('1810212273', '25d55ad283aa400af464c76d713c07ad', '睦乐冬', '6');
INSERT INTO `t_student` VALUES ('1810212274', '25d55ad283aa400af464c76d713c07ad', '司恨珍', '6');
INSERT INTO `t_student` VALUES ('1810212275', '25d55ad283aa400af464c76d713c07ad', '费莫巧风', '6');
INSERT INTO `t_student` VALUES ('1810212276', '25d55ad283aa400af464c76d713c07ad', '谈新真', '6');
INSERT INTO `t_student` VALUES ('1810212277', '25d55ad283aa400af464c76d713c07ad', '真亦瑶', '6');
INSERT INTO `t_student` VALUES ('1810212278', '25d55ad283aa400af464c76d713c07ad', '范访波', '6');
INSERT INTO `t_student` VALUES ('1810212279', '25d55ad283aa400af464c76d713c07ad', '凭亦凝', '6');

-- ----------------------------
-- Table structure for t_student_class
-- ----------------------------
DROP TABLE IF EXISTS `t_student_class`;
CREATE TABLE `t_student_class` (
  `class_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `class_name` varchar(255) DEFAULT NULL COMMENT '班级名称',
  `department_id` int(11) unsigned DEFAULT NULL COMMENT '班级所属学院',
  `grade_id` int(11) unsigned DEFAULT NULL,
  `major_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  KEY `class_college` (`department_id`),
  KEY `class_grade` (`grade_id`),
  KEY `class_major` (`major_id`),
  CONSTRAINT `class_college` FOREIGN KEY (`department_id`) REFERENCES `t_department` (`department_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `class_grade` FOREIGN KEY (`grade_id`) REFERENCES `t_grade` (`grade_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `class_major` FOREIGN KEY (`major_id`) REFERENCES `t_major` (`major_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student_class
-- ----------------------------
INSERT INTO `t_student_class` VALUES ('1', '计科1801', '9', '2', '1');
INSERT INTO `t_student_class` VALUES ('2', '计科1901', '9', '1', '1');
INSERT INTO `t_student_class` VALUES ('3', '汉语1801', '1', '2', '2');
INSERT INTO `t_student_class` VALUES ('4', '旅管1801', '5', '2', '3');
INSERT INTO `t_student_class` VALUES ('5', '英语1801', '6', '2', '4');
INSERT INTO `t_student_class` VALUES ('6', '统计1801', '2', '2', '5');
INSERT INTO `t_student_class` VALUES ('7', '体育1801', '7', '2', '6');

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `work_id` int(11) unsigned NOT NULL COMMENT '工号',
  `teacher_secret` varchar(255) NOT NULL COMMENT '密码',
  `role_id` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '角色id',
  `department_id` int(255) unsigned DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `professional_id` int(10) unsigned DEFAULT NULL,
  `state_id` int(11) unsigned DEFAULT NULL COMMENT '教师状态  进修、访学、长期请假',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('1000000001', '25d55ad283aa400af464c76d713c07ad', '4', '9', '永宛阳', '1', '2');
INSERT INTO `t_teacher` VALUES ('1000000002', '25d55ad283aa400af464c76d713c07ad', '3', '2', '磨安阳', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000003', '25d55ad283aa400af464c76d713c07ad', '2', '9', '督寒绿', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000004', '25d55ad283aa400af464c76d713c07ad', '2', '9', '夹谷巧兰', '2', '1');
INSERT INTO `t_teacher` VALUES ('1000000005', '25d55ad283aa400af464c76d713c07ad', '2', '7', '不问萱', '3', '1');
INSERT INTO `t_teacher` VALUES ('1000000006', '25d55ad283aa400af464c76d713c07ad', '2', '4', '濮阳醉珊', '4', '1');
INSERT INTO `t_teacher` VALUES ('1000000007', '25d55ad283aa400af464c76d713c07ad', '2', '2', '乙雪珍', '2', '1');
INSERT INTO `t_teacher` VALUES ('1000000008', '25d55ad283aa400af464c76d713c07ad', '2', '8', '硕旭昇', '4', '2');
INSERT INTO `t_teacher` VALUES ('1000000009', '25d55ad283aa400af464c76d713c07ad', '2', '5', '百里雨欣', '4', '1');
INSERT INTO `t_teacher` VALUES ('1000000010', '25d55ad283aa400af464c76d713c07ad', '2', '10', '抄兴云', '2', '1');
INSERT INTO `t_teacher` VALUES ('1000000015', '25d55ad283aa400af464c76d713c07ad', '2', '1', '车乐曼', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000016', '25d55ad283aa400af464c76d713c07ad', '2', '1', '愈乐安', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000017', '25d55ad283aa400af464c76d713c07ad', '2', '1', '驹之彤', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000018', '25d55ad283aa400af464c76d713c07ad', '2', '1', '革友枫', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000019', '25d55ad283aa400af464c76d713c07ad', '2', '1', '谭香菱', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000020', '25d55ad283aa400af464c76d713c07ad', '3', '2', '闫香蓉', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000021', '25d55ad283aa400af464c76d713c07ad', '2', '2', '公孙幼旋', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000022', '25d55ad283aa400af464c76d713c07ad', '2', '2', '蚁孤云', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000023', '25d55ad283aa400af464c76d713c07ad', '2', '2', '答易绿', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000024', '25d55ad283aa400af464c76d713c07ad', '3', '2', '林春柔', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000025', '25d55ad283aa400af464c76d713c07ad', '3', '2', '廖秋香', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000026', '25d55ad283aa400af464c76d713c07ad', '3', '2', '矫沛山', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000027', '25d55ad283aa400af464c76d713c07ad', '2', '2', '赢山蝶', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000028', '25d55ad283aa400af464c76d713c07ad', '2', '2', '司寇醉容', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000029', '25d55ad283aa400af464c76d713c07ad', '2', '2', '励冷天', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000030', '25d55ad283aa400af464c76d713c07ad', '2', '3', '阴幼凡', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000031', '25d55ad283aa400af464c76d713c07ad', '2', '3', '望冰之', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000032', '25d55ad283aa400af464c76d713c07ad', '2', '3', '水惜曼', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000033', '25d55ad283aa400af464c76d713c07ad', '2', '3', '昂飞烟', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000034', '25d55ad283aa400af464c76d713c07ad', '2', '3', '隐慕山', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000035', '25d55ad283aa400af464c76d713c07ad', '2', '3', '邸从蓉', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000036', '25d55ad283aa400af464c76d713c07ad', '2', '3', '郁谷蓝', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000037', '25d55ad283aa400af464c76d713c07ad', '2', '3', '沃芷梦', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000038', '25d55ad283aa400af464c76d713c07ad', '2', '3', '韩风', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000039', '25d55ad283aa400af464c76d713c07ad', '2', '4', '承慕丹', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000040', '25d55ad283aa400af464c76d713c07ad', '2', '4', '寒山桃', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000041', '25d55ad283aa400af464c76d713c07ad', '2', '4', '爱怀蕾', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000042', '25d55ad283aa400af464c76d713c07ad', '2', '4', '仆雪晴', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000043', '25d55ad283aa400af464c76d713c07ad', '2', '4', '殷夜风', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000044', '25d55ad283aa400af464c76d713c07ad', '2', '4', '步半梅', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000045', '25d55ad283aa400af464c76d713c07ad', '2', '4', '平平卉', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000046', '25d55ad283aa400af464c76d713c07ad', '2', '4', '滑碧凡', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000047', '25d55ad283aa400af464c76d713c07ad', '2', '4', '伯梦竹', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000048', '25d55ad283aa400af464c76d713c07ad', '2', '5', '席绮之', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000049', '25d55ad283aa400af464c76d713c07ad', '2', '5', '丘绫', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000050', '25d55ad283aa400af464c76d713c07ad', '2', '5', '忻安真', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000051', '25d55ad283aa400af464c76d713c07ad', '2', '5', '泷映雁', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000052', '25d55ad283aa400af464c76d713c07ad', '2', '5', '乙寄灵', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000053', '25d55ad283aa400af464c76d713c07ad', '2', '5', '典惜蕊', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000054', '25d55ad283aa400af464c76d713c07ad', '2', '5', '居冬山', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000055', '25d55ad283aa400af464c76d713c07ad', '2', '5', '印冷天', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000056', '25d55ad283aa400af464c76d713c07ad', '2', '5', '来雅雪', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000057', '25d55ad283aa400af464c76d713c07ad', '2', '5', '富察采珊', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000058', '25d55ad283aa400af464c76d713c07ad', '2', '5', '夏侯天蓉', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000059', '25d55ad283aa400af464c76d713c07ad', '2', '6', '雪绮彤', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000060', '25d55ad283aa400af464c76d713c07ad', '2', '6', '帅新儿', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000061', '25d55ad283aa400af464c76d713c07ad', '2', '6', '锺秋巧', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000062', '25d55ad283aa400af464c76d713c07ad', '2', '6', '呼含珊', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000063', '25d55ad283aa400af464c76d713c07ad', '2', '6', '裘尔槐', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000064', '25d55ad283aa400af464c76d713c07ad', '3', '6', '通绮南', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000065', '25d55ad283aa400af464c76d713c07ad', '2', '6', '介依竹', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000066', '25d55ad283aa400af464c76d713c07ad', '2', '6', '虞凡菱', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000067', '25d55ad283aa400af464c76d713c07ad', '2', '7', '司采雪', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000068', '25d55ad283aa400af464c76d713c07ad', '2', '7', '池冬卉', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000069', '25d55ad283aa400af464c76d713c07ad', '2', '7', '慎香凡', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000070', '25d55ad283aa400af464c76d713c07ad', '2', '7', '澹台梦寒', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000071', '25d55ad283aa400af464c76d713c07ad', '3', '7', '姚小凝', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000072', '25d55ad283aa400af464c76d713c07ad', '2', '7', '储裳', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000073', '25d55ad283aa400af464c76d713c07ad', '2', '7', '花如兰', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000074', '25d55ad283aa400af464c76d713c07ad', '2', '8', '宏冷南', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000075', '25d55ad283aa400af464c76d713c07ad', '2', '8', '铎环', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000076', '25d55ad283aa400af464c76d713c07ad', '2', '8', '法新丹', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000077', '25d55ad283aa400af464c76d713c07ad', '2', '8', '亓官香阳', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000078', '25d55ad283aa400af464c76d713c07ad', '2', '8', '蓝沛亦', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000079', '25d55ad283aa400af464c76d713c07ad', '2', '8', '微生语蓝', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000080', '25d55ad283aa400af464c76d713c07ad', '3', '8', '郭盼芙', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000081', '25d55ad283aa400af464c76d713c07ad', '3', '8', '关笑容', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000082', '25d55ad283aa400af464c76d713c07ad', '3', '9', '合幼儿', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000083', '25d55ad283aa400af464c76d713c07ad', '2', '9', '贸初桃', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000084', '25d55ad283aa400af464c76d713c07ad', '2', '9', '家觅雪', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000085', '25d55ad283aa400af464c76d713c07ad', '2', '9', '汪语云', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000086', '25d55ad283aa400af464c76d713c07ad', '2', '9', '布惜珊', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000087', '25d55ad283aa400af464c76d713c07ad', '2', '9', '尾曼云', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000088', '25d55ad283aa400af464c76d713c07ad', '2', '9', '辉从珊', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000089', '25d55ad283aa400af464c76d713c07ad', '2', '9', '侍觅曼', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000090', '25d55ad283aa400af464c76d713c07ad', '2', '9', '虢晓菡', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000091', '25d55ad283aa400af464c76d713c07ad', '2', '10', '枚念雁', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000092', '25d55ad283aa400af464c76d713c07ad', '2', '10', '弘雁卉', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000093', '25d55ad283aa400af464c76d713c07ad', '2', '10', '国白松', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000094', '25d55ad283aa400af464c76d713c07ad', '2', '10', '晋平青', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000095', '25d55ad283aa400af464c76d713c07ad', '2', '10', '马芷枫', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000096', '25d55ad283aa400af464c76d713c07ad', '2', '10', '钮怀丹', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000097', '25d55ad283aa400af464c76d713c07ad', '2', '10', '谢诗蕾', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000098', '25d55ad283aa400af464c76d713c07ad', '2', '11', '郝幼枫', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000099', '25d55ad283aa400af464c76d713c07ad', '2', '11', '昌晓露', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000100', '25d55ad283aa400af464c76d713c07ad', '2', '11', '由云', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000101', '25d55ad283aa400af464c76d713c07ad', '2', '11', '字乐双', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000102', '25d55ad283aa400af464c76d713c07ad', '2', '12', '之幻玉', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000103', '25d55ad283aa400af464c76d713c07ad', '2', '12', '霍亦丝', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000104', '25d55ad283aa400af464c76d713c07ad', '2', '12', '第五乐巧', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000105', '25d55ad283aa400af464c76d713c07ad', '2', '12', '双冷绿', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000106', '25d55ad283aa400af464c76d713c07ad', '2', '12', '仝芷冬', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000107', '25d55ad283aa400af464c76d713c07ad', '2', '12', '圭映真', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000108', '25d55ad283aa400af464c76d713c07ad', '2', '12', '镇梦山', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000109', '25d55ad283aa400af464c76d713c07ad', '2', '13', '竺傲瑶', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000110', '25d55ad283aa400af464c76d713c07ad', '2', '13', '郑香冬', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000111', '25d55ad283aa400af464c76d713c07ad', '2', '13', '少尔烟', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000112', '25d55ad283aa400af464c76d713c07ad', '2', '13', '千芷蕊', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000113', '25d55ad283aa400af464c76d713c07ad', '2', '13', '南门寄柳', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000114', '25d55ad283aa400af464c76d713c07ad', '2', '14', '钦书兰', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000115', '25d55ad283aa400af464c76d713c11ad', '2', '15', '西门寻山', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000116', '25d55ad283aa400af464c76d713c12ad', '2', '15', '蹉孤霜', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000117', '25d55ad283aa400af464c76d713c13ad', '2', '15', '闪半松', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000118', '25d55ad283aa400af464c76d713c14ad', '2', '16', '弭强圉', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000119', '25d55ad283aa400af464c76d713c15ad', '2', '16', '党清照', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000120', '25d55ad283aa400af464c76d713c16ad', '2', '16', '原之卉', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000121', '25d55ad283aa400af464c76d713c17ad', '2', '16', '腾山寒', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000122', '25d55ad283aa400af464c76d713c18ad', '2', '16', '翦丹烟', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000123', '25d55ad283aa400af464c76d713c19ad', '2', '17', '谯尔阳', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000124', '25d55ad283aa400af464c76d713c20ad', '2', '17', '剧白梅', '1', '1');
INSERT INTO `t_teacher` VALUES ('1000000125', '25d55ad283aa400af464c76d713c21ad', '1', '17', '沈凌云', '1', '1');

-- ----------------------------
-- Table structure for t_teacher_state
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher_state`;
CREATE TABLE `t_teacher_state` (
  `state_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher_state
-- ----------------------------
INSERT INTO `t_teacher_state` VALUES ('1', '参与本次评教');
INSERT INTO `t_teacher_state` VALUES ('2', '不参与本次评教');

-- ----------------------------
-- Table structure for t_term
-- ----------------------------
DROP TABLE IF EXISTS `t_term`;
CREATE TABLE `t_term` (
  `term_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `term_name` varchar(255) DEFAULT NULL COMMENT '学期',
  PRIMARY KEY (`term_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_term
-- ----------------------------
INSERT INTO `t_term` VALUES ('1', '2019-09-02 16:53:31', '2020-02-01 16:54:31', '2019-2020学年第一学期');
INSERT INTO `t_term` VALUES ('2', '2020-03-01 16:55:03', '2020-07-10 16:55:11', '2019-2020学年第二学期');
INSERT INTO `t_term` VALUES ('3', '2019-02-25 00:00:00', '2018-07-14 00:00:00', '2018-2019学年第二学期');
INSERT INTO `t_term` VALUES ('4', '2019-09-02 00:00:00', '2020-01-17 00:00:00', '2019-2020学年第一学期');
INSERT INTO `t_term` VALUES ('5', '2020-09-02 00:00:00', '2021-01-17 00:00:00', '2020-2021学年第一学期');
INSERT INTO `t_term` VALUES ('6', '2021-02-04 00:00:00', '2021-07-14 00:00:00', '2020-2021学年第二学期');

-- ----------------------------
-- Table structure for t_third_kpi
-- ----------------------------
DROP TABLE IF EXISTS `t_third_kpi`;
CREATE TABLE `t_third_kpi` (
  `third_kpi_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `second_kpi_id` int(11) unsigned DEFAULT NULL,
  `third_kpi_content` varchar(255) DEFAULT NULL,
  `calculation_rule_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`third_kpi_id`),
  KEY `calculation_rule` (`calculation_rule_id`),
  KEY `second_kpi` (`second_kpi_id`),
  CONSTRAINT `calculation_rule` FOREIGN KEY (`calculation_rule_id`) REFERENCES `t_calculation_rule` (`calculation_rule_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `second_kpi` FOREIGN KEY (`second_kpi_id`) REFERENCES `t_second_kpi` (`second_kpi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_third_kpi
-- ----------------------------
INSERT INTO `t_third_kpi` VALUES ('1', '28', '老师对授课精心准备', '1');
INSERT INTO `t_third_kpi` VALUES ('2', '28', '老师讲课时充满激情', '1');
INSERT INTO `t_third_kpi` VALUES ('3', '28', '老师的授课风格很吸引人', '1');
INSERT INTO `t_third_kpi` VALUES ('4', '29', '老师能阐述所讲概念的背景或来源', '1');
INSERT INTO `t_third_kpi` VALUES ('5', '29', '老师对课程内容的讲授清楚易懂', '1');
INSERT INTO `t_third_kpi` VALUES ('6', '29', '老师能将教学内容与实际应用相结合', '1');
INSERT INTO `t_third_kpi` VALUES ('7', '29', '老师能充分介绍学科领域的最新进展', '1');
INSERT INTO `t_third_kpi` VALUES ('8', '30', '老师每次课都有明确的教学目标', '1');
INSERT INTO `t_third_kpi` VALUES ('9', '30', '教师能够营造有序的课堂环境，学生抬头率高', '1');
INSERT INTO `t_third_kpi` VALUES ('10', '30', '老师有效地组织了课堂讨论，学生参与率高', '1');
INSERT INTO `t_third_kpi` VALUES ('11', '31', '老师鼓励学生表达自己的观点', '1');
INSERT INTO `t_third_kpi` VALUES ('12', '31', '老师鼓励学生提问并耐心解答', '1');
INSERT INTO `t_third_kpi` VALUES ('13', '32', '老师尊重每个学生并以礼相待', '1');
INSERT INTO `t_third_kpi` VALUES ('14', '32', '老师课内外都乐于帮助学生', '1');
INSERT INTO `t_third_kpi` VALUES ('15', '33', '老师布置的作业有助于对课程的理解', '1');
INSERT INTO `t_third_kpi` VALUES ('16', '33', '老师考核学生平时成绩的方法公平恰当', '1');
INSERT INTO `t_third_kpi` VALUES ('17', '33', '老师对考核及作业的反馈很及时', '1');
INSERT INTO `t_third_kpi` VALUES ('18', '34', '通过该课程的学习,我觉得很有收获，知识能力素质都有很大提升', '1');
INSERT INTO `t_third_kpi` VALUES ('19', '34', '老师的教学激发了我学习该课程的兴趣', '1');
INSERT INTO `t_third_kpi` VALUES ('20', '34', '我分析解决相关问题的能力提高了', '1');
INSERT INTO `t_third_kpi` VALUES ('21', '35', '遵守教学纪律情况', '1');
INSERT INTO `t_third_kpi` VALUES ('22', '35', '课前教学文案的准备情况', '1');
INSERT INTO `t_third_kpi` VALUES ('23', '35', '教师语言表达与板书书写', '1');
INSERT INTO `t_third_kpi` VALUES ('24', '36', '教学目标的设定', '1');
INSERT INTO `t_third_kpi` VALUES ('25', '36', '教学设计和教学方法的选择', '1');
INSERT INTO `t_third_kpi` VALUES ('26', '36', '教学内容的讲解方面', '1');
INSERT INTO `t_third_kpi` VALUES ('27', '36', '组织课堂讨论情况', '1');
INSERT INTO `t_third_kpi` VALUES ('28', '36', '课堂提供案例情况', '1');
INSERT INTO `t_third_kpi` VALUES ('29', '36', '师生互动交流情况', '1');
INSERT INTO `t_third_kpi` VALUES ('30', '36', '教学内容中学术前沿介绍', '1');
INSERT INTO `t_third_kpi` VALUES ('31', '36', '课堂作业布置情况', '1');
INSERT INTO `t_third_kpi` VALUES ('32', '38', '教学媒体辅助手段的选择', '1');
INSERT INTO `t_third_kpi` VALUES ('33', '38', '信息化教学手段的运用', '1');
INSERT INTO `t_third_kpi` VALUES ('34', '38', '混合式教学方法的使用情况', '1');
