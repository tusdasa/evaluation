const express = require('express');
const router = express.Router();
const axios = require('axios');
const util = require('../uitls/utils');

router.get('/', function (req, res, next) {
    if (req.session.token == null || util.check(req.session.token) == null) {
        res.redirect("/");
    }
    axios({
            url: "http://localhost:8080/service/studentassessment/",
            method: 'get',
            headers: {"Authorization": req.session.token}
        }
    ).then(function (response) {
        if (response.data.code == 200) {
            res.render("evaluation", {kpi: response.data.table, msg: null})
        } else {
            res.render("evaluation", {kpi: null, msg: response.data.message});
        }
        y
    }).catch(function (error) {
        res.send(error);
    });
});

module.exports = router;
