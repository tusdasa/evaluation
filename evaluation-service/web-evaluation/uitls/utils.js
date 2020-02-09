const jwt = require('jsonwebtoken');
exports.check = function (token) {
    try {
        let decoded = jwt.verify(token, '9O2B+amfn52nzowCysmC7+hfuA0q9xvxcgLU1UDVYwM=');
        return decoded;
    } catch (err) {
        return null;
    }
}