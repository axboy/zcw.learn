var exec = require('cordova/exec');

exports.phoneInfo = function(arg0, success, error) {
    exec(success, error, "MyPlugin", "xxxxx", []);
};