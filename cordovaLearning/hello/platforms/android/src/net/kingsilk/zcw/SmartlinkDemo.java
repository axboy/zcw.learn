package net.kingsilk.zcw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;
import net.kingsilk.activity.CustomizedActivity;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import com.hiflying.smartlink.ISmartLinker;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.SmartLinkedModule;
import com.hiflying.smartlink.v7.MulticastSmartLinker;

/**
 * Created by zcw on 2016/11/23.
 */
public class SmartlinkDemo extends CordovaPlugin implements OnSmartLinkListener {

	private ISmartLinker mSnifferSmartLinker;
	private boolean mIsConncting = false;
	private Activity activity;

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		activity = this.cordova.getActivity();
		if (action.equals("goActivity")) {
			// 报错,回去看,eclipse看不到错误信息
			Intent intent = new Intent(activity, CustomizedActivity.class);
			intent.putExtra(CustomizedActivity.EXTRA_SMARTLINK_VERSION, 7);
			activity.startActivity(intent);
			callbackContext.success("goActivity");
			return true;
		} else if (action.equals("getSSID")) {
			String ssid = this.getWifiSSID();
			callbackContext.success(ssid);
			return true;
		} else if (action.equals("start")) {
			mSnifferSmartLinker = MulticastSmartLinker.getInstance();
			this.start(args.getString(0), args.getString(1));
			callbackContext.success();
			return true;
		}
		callbackContext.success();
		return true;
	}

	private String getWifiSSID() {
		WifiManager wm = (WifiManager) this.cordova.getActivity().getSystemService(Context.WIFI_SERVICE);
		WifiInfo wi = wm.getConnectionInfo();
		String ssid = wi.getSSID();
		Log.d("wifiInfo", wi.toString());
		return ssid;
		/*
		if (ssid.length() > 2 && ssid.startsWith("\"") && ssid.endsWith("\"")) {
			return ssid.substring(1, ssid.length() - 1);
		} else {
			return ssid;
		}*/
	}

	private void start(String ssid, String password) {
		if (!mIsConncting) {
			// 设置要配置的ssid 和pswd
			try {
				// mSnifferSmartLinker.setOnSmartLinkListener(activity);
				// 开始 smartLink
				mSnifferSmartLinker.start(activity.getApplicationContext(), password, ssid);
				mIsConncting = true;
				Toast.makeText(activity, "正在连接,请稍候...", Toast.LENGTH_LONG).show();
				// mWaitingDialog.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onCompleted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLinked(SmartLinkedModule arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTimeOut() {
		// TODO Auto-generated method stub

	}
}
