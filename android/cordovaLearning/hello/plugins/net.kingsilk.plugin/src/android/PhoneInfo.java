package net.kingsilk.plugin;

import android.content.Context;
import android.telephony.TelephonyManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by zcw on 2016/11/23.
 */
public class PhoneInfo extends CordovaPlugin {

    private TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        callbackContext.success(this.getPhoneNum());
        return true;
    }

    /**
     * 获取手机号
     *
     * @return
     */
    public String getPhoneNum() {
        return tm.getLine1Number();
    }
}
