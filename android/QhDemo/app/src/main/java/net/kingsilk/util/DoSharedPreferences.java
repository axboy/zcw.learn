package net.kingsilk.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zcw on 2016/11/29.
 */
public class DoSharedPreferences {
    private final static String TAG = "DoSharedPreferences";

    public boolean setWifiConf(Context context, String ip, String port) {
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(ip);
        if (!mat.find()) {
            Toast.makeText(context, "ip地址格式错误", Toast.LENGTH_SHORT).show();
            return false;
        }
        rexp = "^[0-9]*[1-9][0-9]*$";
        pat = Pattern.compile(rexp);
        mat = pat.matcher(port);
        if (!mat.find()) {
            Toast.makeText(context, "端口号错误", Toast.LENGTH_SHORT).show();
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences("WIFI_CONF", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ip", ip);
        editor.putString("port", port);
        editor.commit();
        return true;
    }

    public boolean setMacAddr(Context context, String mac) {
        SharedPreferences sp = context.getSharedPreferences("WIFI_CONF", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("mac", mac);
        editor.commit();
        return true;
    }

    public String getIp(Context context) {
        SharedPreferences sp = context.getSharedPreferences("WIFI_CONF", context.MODE_PRIVATE);
        return sp.getString("ip", "10.10.100.254");
    }

    public int getPort(Context context) {
        SharedPreferences sp = context.getSharedPreferences("WIFI_CONF", context.MODE_PRIVATE);
        return Integer.parseInt(sp.getString("port", "8899"));
    }

    public String getMac(Context context) {
        SharedPreferences sp = context.getSharedPreferences("WIFI_CONF", context.MODE_PRIVATE);
        return sp.getString("mac", "unknow");
    }
}
