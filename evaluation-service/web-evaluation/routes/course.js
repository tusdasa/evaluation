const express = require('express');
const router = express.Router();
const utils = require('../uitls/utils')

router.get("/", function (req, res, next) {
    if (req.session.token == undefined || req.session.token == null) {
        res.redirect("/");
    }
    utils.request({
        url: 'evaluation/course',
        method: 'get',
        headers: {"Authorization": req.session.token}
    }, function (response) {
        if (response.data.code == 200) {
            res.render("course", {course: response.data.table, msg: null})
        } else {
            res.render("course", {course: null, msg: response.data.message});
        }
    }, function f(error) {
        res.send(error);
    });
});

module.exports = router;