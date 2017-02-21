package net.kingsilk.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hiflying.smartlink.ISmartLinker;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.SmartLinkedModule;
import com.hiflying.smartlink.v7.MulticastSmartLinker;

import android.content.DialogInterface.OnDismissListener;

import net.kingsilk.util.DoSharedPreferences;

public class SettingActivity extends AppCompatActivity implements OnSmartLinkListener {

    private EditText mSsidEditText;
    private EditText mPasswordEditText;
    private Button mStartButton;

    private ISmartLinker mSnifferSmartLinker;
    private ProgressDialog mWaitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mSsidEditText = (EditText) findViewById(R.id.editText_ssid);
        mPasswordEditText = (EditText) findViewById(R.id.editText_pwd);
        mStartButton = (Button) findViewById(R.id.start_setting);
        mSsidEditText.setText(this.getWifiSsid());
        mSnifferSmartLinker = MulticastSmartLinker.getInstance();
        mWaitingDialog = new ProgressDialog(this);

        mWaitingDialog.setMessage("配置中，请稍等。。。");
        mWaitingDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        mWaitingDialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mSnifferSmartLinker.setOnSmartLinkListener(null);
                mSnifferSmartLinker.stop();
            }
        });
    }

    @Override
    public void onLinked(SmartLinkedModule arg0) {
        Toast.makeText(getApplicationContext(), "配网成功", Toast.LENGTH_SHORT).show();
        new DoSharedPreferences().setWifiConf(this, arg0.getIp(), "8899");
        new DoSharedPreferences().setMacAddr(this, arg0.getMac());
        mWaitingDialog.dismiss();
    }

    @Override
    public void onCompleted() {
        Toast.makeText(getApplicationContext(), "配网完成!", Toast.LENGTH_SHORT).show();
        mWaitingDialog.dismiss();
    }

    @Override
    public void onTimeOut() {
        Toast.makeText(getApplicationContext(), "超时", Toast.LENGTH_SHORT).show();
        mWaitingDialog.dismiss();
    }

    public String getWifiSsid() {
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        if (wm != null) {
            WifiInfo wi = wm.getConnectionInfo();
            if (wi != null) {
                String ssid = wi.getSSID();
                if (ssid.length() > 2 && ssid.startsWith("\"") && ssid.endsWith("\"")) {
                    return ssid.substring(1, ssid.length() - 1);
                } else {
                    return ssid;
                }
            }
        }
        return null;
    }

    public void start(View view) {
        try {
            mSnifferSmartLinker.setOnSmartLinkListener(this);
            // 开始 smartLink
            mSnifferSmartLinker.start(this, mPasswordEditText.getText().toString(), mSsidEditText.getText().toString());
            Toast.makeText(this, "正在连接,请稍候...", Toast.LENGTH_SHORT).show();
            mWaitingDialog.show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
