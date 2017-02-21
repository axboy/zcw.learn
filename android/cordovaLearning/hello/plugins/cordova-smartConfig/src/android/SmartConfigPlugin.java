package net.kingsilk.www;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.hiflying.smartlink.ISmartLinker;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.SmartLinkedModule;
import com.hiflying.smartlink.v7.MulticastSmartLinker;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartConfigPlugin extends CordovaPlugin implements OnSmartLinkListener {

    private static String TAG = "SmartConfigPlugin";

    private ISmartLinker mSnifferSmartLinker;
    private boolean mIsConncting = false;
    private Activity activity;
    private CallbackContext callbackContext;
    protected ProgressDialog mWaitingDialog;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) {
        try {
            //JSONObject arguments = args.getJSONObject(0);
            //final String params = arguments.getString("params");
            this.callbackContext = callbackContext;
            activity = this.cordova.getActivity();
            if (action.equals("start")) {
                mSnifferSmartLinker = MulticastSmartLinker.getInstance();
                this.start(args.getString(0), args.getString(1));
            } else if (action.equals("ssid")) {
                String ssid = this.getWifiSSID();
                callbackContext.success(ssid);
            }
            /*cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {

                }
            });*/
        } catch (JSONException e) {
            callbackContext.error(new JSONObject());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String getWifiSSID() {
        WifiManager wm = (WifiManager) this.cordova.getActivity().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wi = wm.getConnectionInfo();
        String ssid = wi.getSSID();
        if (ssid.length() > 2 && ssid.startsWith("\"") && ssid.endsWith("\"")) {
            return ssid.substring(1, ssid.length() - 1);
        } else {
            return ssid;
        }
    }

    private void start(String ssid, String password) {

        mWaitingDialog = new ProgressDialog(activity);
        mWaitingDialog.setMessage("正在连接，请稍后。。。");
        mWaitingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mSnifferSmartLinker.setOnSmartLinkListener(null);
                mSnifferSmartLinker.stop();
                mIsConncting = false;
            }
        });
        if (!mIsConncting) {
            // 设置要配置的ssid 和pswd
            try {
                mSnifferSmartLinker.setOnSmartLinkListener(SmartConfigPlugin.this);
                // 开始 smartLink
                mSnifferSmartLinker.start(activity.getApplicationContext(), password, ssid);
                mIsConncting = true;
                //Toast.makeText(activity, "正在连接,请稍候...", Toast.LENGTH_LONG).show();
                mWaitingDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCompleted() {
        this.closeDialog();
        callbackContext.success("{code:\"error\",msg:\"completed\"}");
    }

    @Override
    public void onLinked(SmartLinkedModule arg0) {
        this.closeDialog();
        callbackContext.success("code:\"success\",ip:\"" + arg0.getIp() + "\",mac:\"" + arg0.getMac() + "\"");
    }

    @Override
    public void onTimeOut() {
        this.closeDialog();
        callbackContext.success("{code:\"error\",msg:\"time out\"}");
    }

    /**
     * 关闭dialog
     */
    private void closeDialog() {
        if (mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
        }
    }
}
