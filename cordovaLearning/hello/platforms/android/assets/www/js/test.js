//这个js随便创建的，引入到html中即可
var myToast = {
	javaShow : function() {
		show(null, null, "MyToast", "javaShow", []);
	},
	javaShowJs : function() {
		show(null, null, "MyToast", "javaShowJs", [ 'js content' ]);
	},
	jsShowJava : function() {
		show(succ, err, "MyToast", "jsShowJava", []);
	},
	jsShowJs : function() {
		show(succ, err, "MyToast", "jsShowJs", [ 'js content1' ]);
	}
};

var phoneInfo = function() {
	myPlugin.phoneInfo(null, succ, err);
};

var goActivity = function() {
	show(succ, err, "SmartlinkDemo", "goActivity", []);
};

var getWifiSSID = function() {
	show(succ, err, "SmartlinkDemo", "getSSID", []);
};

var start = function() {
	show(succ, err, "SmartlinkDemo", "start", [ 'ssid', 'password' ]);
};

var show = function(successCallback, errorCallback, className, action, arr) {
	cordova.exec(successCallback, // success callback function
	errorCallback, // error callback function
	className, // mapped to our native Java class called "MyToast",
	action, // action name
	arr // this array of custom arguments to create our entry
	);
};

var succ = function(resp) {
	alert('succ:' + resp);
};

var err = function(resp) {
	alert('err:' + resp);
};