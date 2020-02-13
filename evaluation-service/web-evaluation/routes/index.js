const express = require('express');
const router = express.Router();
const axios = require('axios');
const utils = require('../uitls/utils')
axios.defaults.headers.common['Authorization'] = "";
router.get('/', function (req, res, next) {
    res.render('auth', {msg: null});
});

router.post("/auth", function (req, res, next) {
    let params = new URLSearchParams();
    let username = req.body['username'];
    let password = req.body["password"];
    params.append("studentId", username);
    params.append("password", password);
    if (username != null && password != null) {
        utils.request({
            url: 'auth/student',
            method: 'POST',
            params: params,
            headers: {"Content-Type": "application/x-www-form-urlencoded"}
        }, function (response) {
            if (response.data.code == 200) {
                req.session.token = response.data.data;
                res.redirect("/course");
            } else {
                res.render('auth', {msg: response.data.message});
            }
        }, function (error) {
            res.render('auth', {msg: error});
        })
    } else {
        res.render('auth', {msg: null});
    }
})

module.exports = router;
