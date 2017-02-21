package net.kingsilk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 配网
     *
     * @param view
     */
    public void goSetting(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        this.startActivity(intent);
    }

    /**
     * 局域网调试
     *
     * @param view
     */
    public void goOperate(View view) {
        Intent intent = new Intent(this, OperateActivity.class);
        intent.putExtra("env", "local");
        this.startActivity(intent);
    }

    /**
     * MQTT发送消息
     *
     * @param view
     */
    public void goOperateNet(View view) {
        Intent intent = new Intent(this, OperateActivity.class);
        intent.putExtra("env", "net");
        this.startActivity(intent);
    }
}
