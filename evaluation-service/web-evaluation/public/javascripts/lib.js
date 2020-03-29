function checkFrom(username, password) {
    if (username && password && username.length >= 9 && username.length <= 20 && password.length >= 6 && password.length <= 16) {
        return true
    }
    return false
}

function studentCheck() {
    let studentId = $("#studentId").val();
    let studentPwd = $("#studentPwd").val();
    return checkFrom(studentId, studentPwd);
}

function teacherCheck() {
    let teacherId = $("#teacherId").val();
    let teacherPwd = $("#teacherPwd").val();
    return checkFrom(teacherId, teacherPwd);
}