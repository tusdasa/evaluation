const express = require('express');
const router = express.Router();
const utils = require('../uitls/utils');

/**
 * 教师
 * */

router.get('/', function (req, res, next) {
    if (req.session.token === undefined || req.session.token == null) {
        res.redirect("/");
    }
    utils.request({
        url: 'tevaluation/teacher',
        method: 'get',
        headers: {"Authorization": req.session.token}
    }, function (response) {
        if (response.data.code === 200) {
            res.render("teacher", {teachers: response.data.table, msg: null});
        } else {
            res.send(response.data);
        }
    }, function (error) {
        res.send(error);
    })
});


module.exports = router;
