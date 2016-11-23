cordova.define("net.kingsilk.plugin.myPlugin", function(require, exports, module) {
var exec = require('cordova/exec');

exports.phoneInfo = function(arg0, success, error) {
    exec(success, error, "MyPlugin", "xxxxx", []);
};
});
