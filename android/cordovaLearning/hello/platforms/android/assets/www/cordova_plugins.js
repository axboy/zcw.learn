cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "id": "net.kingsilk.plugin.myPlugin",
        "file": "plugins/net.kingsilk.plugin/www/myPlugin.js",
        "pluginId": "net.kingsilk.plugin",
        "clobbers": [
            "myPlugin"
        ]
    },
    {
        "id": "cordova-smartConfig.smart",
        "file": "plugins/cordova-smartConfig/www/smart.js",
        "pluginId": "cordova-smartConfig",
        "clobbers": [
            "smart"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.3.0",
    "net.kingsilk.plugin": "0.0.1",
    "cordova-smartConfig": "0.0.2"
};
// BOTTOM OF METADATA
});