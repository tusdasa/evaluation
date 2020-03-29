const express = require('express');
const router = express.Router();
const utils = require('../uitls/utils');

/**
 *
 * */

router.get('/', function (req, res, next) {
    res.render('auth', {msg: null});
});

router.post("/student", function (req, res, next) {
    let params = new URLSearchParams();
    let username = req.body['id'];
    let password = req.body['password'];
    params.append("studentId", username);
    params.append("password", password);
    if (username != null && password != null) {
        utils.request({
            url: 'auth/student',
            method: 'POST',
            params: params,
            headers: {"Content-Type": "application/x-www-form-urlencoded"}
        }, function (response) {
            if (response.data.code === 200) {
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
});

router.post("/teacher", function (req, res, next) {
    let params = new URLSearchParams();
    let username = req.body['id'];
    let password = req.body["password"];
    params.append("workId", username);
    params.append("password", password);
    if (username != null && password != null) {
        utils.request({
            url: 'auth/teacher',
            method: 'POST',
            params: params,
            headers: {"Content-Type": "application/x-www-form-urlencoded"}
        }, function (response) {
            if (response.data.code === 200) {
                req.session.token = response.data.data;
                res.redirect("/teacher");
            } else {
                res.render('auth', {msg: response.data.message});
            }
        }, function (error) {
            res.render('auth', {msg: error});
        })
    } else {
        res.render('auth', {msg: null});
    }
});

module.exports = router;
