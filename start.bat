@echo off
echo ��ʼ���ط���..
start "���ط���" java -jar evaluation-gateway-service.jar
timeout /t 10
echo ��ʼѧ������..
start "ѧ������" java -jar evaluation-student-service.jar
timeout /t 10
echo ��ʼ��ʦ����..
start "��ʦ����" java -jar evaluation-teacher-service.jar
timeout /t 10
echo ��ʼ��֤����..
start "��֤����" java -jar evaluation-authorization-service.jar
timeout /t 10
echo ��ʼѧ�����..
start "ѧ�����" java -jar evaluation-academicyear-service.jar
timeout /t 10
echo ��ʼ�γ̷���..
start "�γ̷���" java -jar evaluation-course-service.jar
timeout /t 10
echo ��ʼ��һָ�����..
start "��һָ��" java -jar evaluation-firstkpi-service.jar
timeout /t 10
echo ��ʼ�ڶ�ָ�����..
start "�ڶ�ָ��" java -jar evaluation-secondkpi-service.jar
timeout /t 10
echo ��ʼ����ָ�����..
start "����ָ��" java -jar evaluation-thirdkpi-service.jar
timeout /t 10
echo ��ʼ��ʦ���۷���..
start "��ʦ����" java -jar teacher-evaluation-service.jar
timeout /t 10
echo ��ʼѧ�����۷���..
start "ѧ������" java -jar student-evaluation-service.jar
timeout /t 10
echo ��ʼ�������ݷ���..
start "��������" java -jar evaluation-data-service.jar
timeout /t 10
echo ��ʼ���ݴ洢����..
start "���ݴ洢" java -jar evaluation-analysis-service.jar
timeout /t 10
echo ��ʼ�������..
start "�������" java -jar evaluation-calculation-service.jar
timeout /t 10
echo ��ʼ���۹������..
start "���۹���" java -jarevaluation-calculationrule-service.jar
timeout /t 10
echo ��ʼ�������..
start "�������" java -jar evaluation-admin-service.jar
timeout /t 10
echo ��ʼ��ά����..
start "��ά���" java -jar evaluation-operations-service.jar
timeout /t 10