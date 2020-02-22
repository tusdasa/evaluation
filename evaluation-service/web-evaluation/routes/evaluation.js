const express = require('express');
const router = express.Router();
const utils = require('../uitls/utils');

router.get('/', function (req, res, next) {
    if (req.session.token == undefined || req.session.token == null) {
        res.redirect("/");
    }

    utils.request({
        url: "evaluation/",
        method: 'get',
        headers: {"Authorization": req.session.token}
    }, function (response) {
        if (response.data.code == 200) {
            res.render("evaluation", {kpi: response.data.table, msg: null, termId: req.query.t, courseId: req.query.c})
        } else {
            res.render("evaluation", {kpi: null, msg: response.data.message, t: null, c: null});
        }
    }, function (error) {
        res.send(error);
    })

});

router.post('/result', function (req, res) {

    console.log(req.body);

    utils.request({
        url: "evaluation/result",
        method: 'post',
        headers: {"Authorization": req.session.token},
        data: req.body
    }, function (response) {
        console.log(response);
        if (response.data.code === 200) {
            res.status(200);
            res.send("成功");
        } else {
            res.status(500);
            res.send("失败");
        }
    }, function (error) {
        res.status(500);
        res.send(error);
    })

})

module.exports = router;
