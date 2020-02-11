const express = require('express');
const router = express.Router();
const utils = require('../uitls/utils')

router.get("/", function (req, res, next) {
    if (req.session.token == null || utils.check(req.session.token) == null) {
        res.redirect("/");
    }
    utils.request({
        url: 'studentassessment/course',
        method: 'get',
        headers: {"Authorization": req.session.token.toString()}
    }, function (response) {
        if (response.data.code == 200) {
            res.render("course", {course: response.data.table, msg: null})
        } else {
            res.render("course", {course: null, msg: response.data.message});
        }
    }, function f(error) {
        res.send(error);
    });
    /*
    axios({
            url: "http://localhost:8080/service/studentassessment/",
            method: 'get',
            headers: {"Authorization": req.session.token}
        }
    ).then(function (response) {
        if (response.data.code == 200) {
            res.render("course", {course: response.data.table, msg: null})
        } else {
            res.render("course", {course: null, msg: response.data.message});
        }
    }).catch(function (error) {
        res.send(error);
    });
     */
});

module.exports = router;