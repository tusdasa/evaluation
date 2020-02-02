function checkFrom(username, password) {
    if (username && password && username.length <= 11 && password.length >= 7 && password.length < 16) {
        return true
    }
    return false
}