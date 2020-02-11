const jwt = require('jsonwebtoken');
const axios = require('axios');

exports.check = function (token) {
    try {
        let decoded = jwt.verify(token, '9O2B+amfn52nzowCysmC7+hfuA0q9xvxcgLU1UDVYwM=');
        return decoded;
    } catch (err) {
        return null;
    }
};

exports.request = function (config, success, failure) {
    const instance = axios.create({
        baseURL: "http://localhost:8080/service/",
        timeout: 5000,
    });
    instance(config).then(function (response) {
        success(response);
    }).catch(function (error) {
        if (error && error.response) {
            switch (error.response.status) {
                case 401:
                    error.message = "身份校验失败，重新登录";
                    break;
                case 404:
                    error.message = "未找到对应服务，请重试";
                    break;
                case 504:
                    error.message = "微服务请求超时，请重试";
            }
        }
        failure(error.message);
    });
};
