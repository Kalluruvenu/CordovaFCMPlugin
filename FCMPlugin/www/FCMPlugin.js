var exec = require('cordova/exec');

exports.getToken = function(arg0, success, error) {
    exec(success, error, "FCMPlugin", "getToken", [arg0]);
};

exports.onR = function(arg0, success, error) {
    exec(success, error, "FCMPlugin", "registerNotification", [arg0]);
};