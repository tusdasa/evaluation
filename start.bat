@echo off
echo 开始网关服务..
start "网关服务" java -jar evaluation-gateway-service.jar
timeout /t 10
echo 开始学生服务..
start "学生服务" java -jar evaluation-student-service.jar
timeout /t 10
echo 开始教师服务..
start "教师服务" java -jar evaluation-teacher-service.jar
timeout /t 10
echo 开始认证服务..
start "认证服务" java -jar evaluation-authorization-service.jar
timeout /t 10
echo 开始学年服务..
start "学年服务" java -jar evaluation-academicyear-service.jar
timeout /t 10
echo 开始课程服务..
start "课程服务" java -jar evaluation-course-service.jar
timeout /t 10
echo 开始第一指标服务..
start "第一指标" java -jar evaluation-firstkpi-service.jar
timeout /t 10
echo 开始第二指标服务..
start "第二指标" java -jar evaluation-secondkpi-service.jar
timeout /t 10
echo 开始第三指标服务..
start "第三指标" java -jar evaluation-thirdkpi-service.jar
timeout /t 10
echo 开始教师评价服务..
start "教师评价" java -jar teacher-evaluation-service.jar
timeout /t 10
echo 开始学生评价服务..
start "学生评价" java -jar student-evaluation-service.jar
timeout /t 10
echo 开始杂项数据服务..
start "杂项数据" java -jar evaluation-data-service.jar
timeout /t 10
echo 开始数据存储服务..
start "数据存储" java -jar evaluation-analysis-service.jar
timeout /t 10
echo 开始计算服务..
start "计算服务" java -jar evaluation-calculation-service.jar
timeout /t 10
echo 开始评价规则服务..
start "评价规则" java -jarevaluation-calculationrule-service.jar
timeout /t 10
echo 开始管理服务..
start "管理服务" java -jar evaluation-admin-service.jar
timeout /t 10
echo 开始运维服务..
start "运维监控" java -jar evaluation-operations-service.jar
timeout /t 10