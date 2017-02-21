/*global cordova, module*/
var exec = require('cordova/exec');
module.exports = {
    hello: function (paymentInfo, successCallback, errorCallback) {
        exec(successCallback, errorCallback, "smart", "hello", [paymentInfo]);
    },
    start: function (paymentInfo, successCallback, errorCallback) {
        exec(successCallback, errorCallback, "smart", "start", [paymentInfo]);
    },
    stop: function (paymentInfo, successCallback, errorCallback) {
        exec(successCallback, errorCallback, "smart", "stop", [paymentInfo]);
    },
	ssid: function (args, successCallback, errorCallback) {
        exec(successCallback, errorCallback, "smart", "ssid", args);
    }
};
