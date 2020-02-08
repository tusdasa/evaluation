var express = require('express');
var router = express.Router();
const axios = require('axios');

/* GET users listing. */
router.get('/', function(req, res, next) {
    if (req.session.token == null) {
        res.redirect("/");
    }
    axios.get('http://localhost:7009/studentassessment/').then(function (response) {

        if (response.data.code == 200) {
            res.render("evaluation", {kpi: response.data.table, msg: null});
        } else {
            res.render("evaluation", {kpi: null, msg: response.data.message})
        }
    }).catch(function (error) {
        res.send(error);
    });
});

module.exports = router;
