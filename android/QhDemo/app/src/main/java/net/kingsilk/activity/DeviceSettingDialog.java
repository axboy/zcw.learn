package net.kingsilk.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;

import net.kingsilk.util.DoSharedPreferences;

public class DeviceSettingDialog extends AppCompatActivity {

    private EditText mEtIp;
    private EditText mEtPort;
    private EditText mEtMac;

    private String env;
    private TableLayout mTlLocal;
    private TableLayout mTlNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_setting_dialog);
        mEtIp = (EditText) findViewById(R.id.et_ip);
        mEtPort = (EditText) findViewById(R.id.et_port);
        mEtMac = (EditText) findViewById(R.id.et_mac);

        mTlLocal = (TableLayout) findViewById(R.id.tl_local);
        mTlNet = (TableLayout) findViewById(R.id.tl_net);

        DoSharedPreferences sp = new DoSharedPreferences();
        mEtIp.setText(sp.getIp(this));
        mEtPort.setText(sp.getPort(this) + "");
        mEtMac.setText(sp.getMac(this));
        this.env = getIntent().getStringExtra("env");
        if (this.env.equals("local")) {
            mTlNet.setVisibility(View.GONE);
        } else {
            mTlLocal.setVisibility(View.GONE);
        }
    }

    public void confirm(View view) {
        Intent intent = new Intent();
        //intent.putExtra("ip", mEtIp.getText().toString());
        //intent.putExtra("port", mEtPort.getText().toString());
        boolean boo = false;
        if (this.env.equals("local")) {
            boo = new DoSharedPreferences().setWifiConf(this, mEtIp.getText().toString(),
                    mEtPort.getText().toString());
            intent.putExtra("env", "local");
        } else {
            boo = new DoSharedPreferences().setMacAddr(this, mEtMac.getText().toString());
            intent.putExtra("env", "net");
        }
        if (boo) {
            this.setResult(0x200, intent);
            finish();
        }
    }

    public void cancel(View view) {
        Intent intent = new Intent();
        this.setResult(0x404, intent);
        finish();
    }
}
