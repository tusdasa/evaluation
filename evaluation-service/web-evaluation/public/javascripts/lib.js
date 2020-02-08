function checkFrom(username, password) {
    if (username && password && username.length >= 10 && username.length <= 20 && password.length >= 7 && password.length < 20) {
        return true
    }
    return false
}