package net.kingsilk.zcw;

import android.app.Activity;
import android.widget.Toast;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by zcw on 2016/11/23.
 */
public class MyToast extends CordovaPlugin{
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        Activity activity = cordova.getActivity();

        if (action.equals("javaShow")) {
            //java 显示内容
            Toast.makeText(activity, "java show...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (action.equals("javaShowJs")) {
            //用java显示js提供的内容
            String str = args.getString(0);
            Toast.makeText(activity, "javaShowJs:" + str, Toast.LENGTH_SHORT).show();
            return true;
        } else if (action.equals("jsShowJava")) {
            //js显示回调的结果
            callbackContext.success("success:java");
            return true;
        } else if (action.equals("jsShowJs")) {
            //js显示java处理之后的内容
            callbackContext.success("jsText:" + args.getString(0));
            return true;
        } else if (action.equals("other")) {
            //其它,略
        }
        callbackContext.success();
        return true;
    }
}
