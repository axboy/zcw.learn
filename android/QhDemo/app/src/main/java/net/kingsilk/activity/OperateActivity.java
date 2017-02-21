package net.kingsilk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import net.kingsilk.util.DoSharedPreferences;
import net.kingsilk.util.StringTools;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class OperateActivity extends AppCompatActivity {

    private TextView mTvDevice;

    ////湿度
    private SeekBar mSbWet;
    private TextView mTvShowWet;

    ////温度
    private SeekBar mSbDry;
    private TextView mTvShowDry;

    ////臭氧含量
    private SeekBar mSbO3;
    private TextView mTvShowO3;

    ////加湿、臭氧、烘干时间
    private TextView mTvWetTime;
    private TextView mTvO3Time;
    private TextView mTvDryTime;

    ////干蒸、杀菌、除螨
    private Switch mSGanZhen;
    private Switch mSShaJun;
    private Switch mSChuMan;
    private Switch mSJiaShi;

    private TextView mTvShowSend;
    private TextView mTvShowReceive;

    private String recMsg = "null";

    //private EditText mEtText;

    private byte[] data = new byte[17];

    private String env = "net";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);

        mTvDevice = (TextView) findViewById(R.id.tv_device);

        mSGanZhen = (Switch) findViewById(R.id.switch_ganzhen);
        mSShaJun = (Switch) findViewById(R.id.switch_shajun);
        mSChuMan = (Switch) findViewById(R.id.switch_chuman);
        mSJiaShi = (Switch) findViewById(R.id.switch_jiashi);

        mSbWet = (SeekBar) findViewById(R.id.sb_wet);
        mTvShowWet = (TextView) findViewById(R.id.tv_show_wet);
        mTvWetTime = (TextView) findViewById(R.id.tv_wet_time);

        mSbDry = (SeekBar) findViewById(R.id.sb_dry);
        mTvShowDry = (TextView) findViewById(R.id.tv_show_dry);
        mTvDryTime = (TextView) findViewById(R.id.tv_dry_time);

        mSbO3 = (SeekBar) findViewById(R.id.sb_o3);
        mTvShowO3 = (TextView) findViewById(R.id.tv_show_o3);
        mTvO3Time = (TextView) findViewById(R.id.tv_o3_time);

        //mEtText = (EditText) findViewById(R.id.et_text);
        //mEtText.clearFocus();

        mTvShowSend = (TextView) findViewById(R.id.tv_show_send);
        mTvShowReceive = (TextView) findViewById(R.id.tv_show_receive);

        this.env = getIntent().getStringExtra("env");
        this.switchUI();

        this.initData();
        this.listener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0x404) {
            return;
        }
        if (data == null) {
            //返回键返回的
            return;
        }
        String time = data.getIntExtra("hour", 0) + ":" + data.getIntExtra("min", 0);
        switch (requestCode) {
            case 0x400:
                //加湿时间
                mTvWetTime.setText(time);
                break;
            case 0x401:
                //臭氧时间
                mTvO3Time.setText(time);
                break;
            case 0x402:
                //烘干时间
                mTvDryTime.setText(time);
                break;
            case 0x500:
                //修改设备ip、端口
                this.env = data.getStringExtra("env");
                this.switchUI();
                break;
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        data[0] = (byte) 0xaa;
        data[1] = (byte) 0xaa;
        data[2] = (byte) 0x0e;
        data[3] = (byte) 0x01;
        for (int i = 4; i < 17; i++) {
            data[i] = 0x00;
        }
    }

    /**
     * 切换ui，设置mac或ip
     */
    private void switchUI() {
        DoSharedPreferences sp = new DoSharedPreferences();
        String str;
        if (this.env.equals("local")) {
            str = sp.getIp(this);
            str += ":" + sp.getPort(this);
        } else {
            str = sp.getMac(this);
        }
        mTvDevice.setText(str);
    }

    /**
     * SeekBar的监听
     */
    private void listener() {
        mSbWet.setProgress(0);
        mSbWet.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvShowWet.setText(mSbWet.getProgress() + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSbDry.setProgress(0);
        mSbDry.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvShowDry.setText(mSbDry.getProgress() + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSbO3.setProgress(0);
        mSbO3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvShowO3.setText(mSbO3.getProgress() + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //设置设备信息
    public void setDevice(View view) {
        Intent intent = new Intent(this, DeviceSettingDialog.class);
        intent.putExtra("env", this.env);
        this.startActivityForResult(intent, 0x500);
    }

    //设置加湿时间
    public void setWetTime(View view) {
        Intent intent = new Intent(this, TimeSettingDialog.class);
        intent.putExtra("time", mTvWetTime.getText().toString());
        this.startActivityForResult(intent, 0x400);
    }

    //设置臭氧时间
    public void setO3Time(View view) {
        Intent intent = new Intent(this, TimeSettingDialog.class);
        intent.putExtra("time", mTvO3Time.getText().toString());
        this.startActivityForResult(intent, 0x401);
    }

    //设置烘干时间
    public void setDryTime(View view) {
        Intent intent = new Intent(this, TimeSettingDialog.class);
        intent.putExtra("time", mTvDryTime.getText().toString());
        this.startActivityForResult(intent, 0x402);
    }

    //开机
    public void startRun(View view) {
        data[4] = 0x01;
        this.doTcp();
    }

    //关机
    public void stopRun(View view) {
        data[4] = 0x02;
        this.doTcp();
    }

    //暂停
    public void pauseRun(View view) {
        data[4] = 0x20;
        this.doTcp();
    }

    //执行
    public void actionRun(View view) {
        data[4] = 0x10;
        if (mSGanZhen.isChecked()) {
            data[5] = (byte) (data[5] | (byte) 0x01);       //干蒸
        } else {
            data[5] = (byte) (data[5] | (byte) 0x02);
        }
        if (mSShaJun.isChecked()) {
            data[5] = (byte) (data[5] | (byte) 0x04);       //杀菌
        } else {
            data[5] = (byte) (data[5] | (byte) 0x08);
        }
        if (mSChuMan.isChecked()) {
            data[5] = (byte) (data[5] | (byte) 0x10);       //除螨
        } else {
            data[5] = (byte) (data[5] | (byte) 0x20);
        }
        if (mSJiaShi.isChecked()) {
            data[5] = (byte) (data[5] | (byte) 0x40);       //加湿
        } else {
            data[5] = (byte) (data[5] | (byte) 0x80);
        }
        data[6] = (byte) mSbWet.getProgress();          //湿度
        data[7] = (byte) mSbO3.getProgress();           //臭氧含量
        data[9] = (byte) mSbDry.getProgress();          //烘干温度
        data[10] = this.getTime(mTvWetTime.getText().toString());       //加湿时间
        data[11] = this.getTime(mTvO3Time.getText().toString());         //臭氧时间
        data[12] = this.getTime(mTvDryTime.getText().toString());         //烘干时间
        data[16] = 0x00;                //校验位
        this.doTcp();
    }

    //发送文本
    @Deprecated
    public void sendText(View view) {
        //this.doTcp(mEtText.getText().toString());
    }

    private byte getTime(String str) {
        String[] list = str.split(":");
        byte b0 = (byte) (Byte.parseByte(list[0]) << 4);
        byte b1 = (byte) (b0 | (Integer.parseInt(list[1]) / 5));
        return b1;
    }

    @Deprecated
    private void doTcp(final String message) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DoSharedPreferences sp = new DoSharedPreferences();
                    Socket socket = new Socket(sp.getIp(OperateActivity.this), sp.getPort(OperateActivity.this));
                    //发送给服务端的消息
                    try {
                        //第二个参数为True则为自动flush
                        PrintWriter out = new PrintWriter(
                                new BufferedWriter(new OutputStreamWriter(
                                        socket.getOutputStream())), true);
                        out.println(message);
                        //out.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        //关闭Socket
                        socket.close();
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void doTcp() {
        data[16] = 0x00;
        byte sum = 0x00;
        for (byte b : this.data) {
            sum += b;
        }
        data[16] = (byte) (sum & 0xff);     //校验位
        mTvShowSend.setText(StringTools.byte2hex(data));
        if (this.env.equals("local")) {
            new MyThread().start();     //局域网连接
        } else {
            new MyMqttThread("dev/cmd/" + mTvDevice.getText().toString()).start();     //mqtt请求
        }
    }

    /**
     * 用于局域网配网
     */
    class MyThread extends Thread {
        @Override
        public void run() {
            Log.d("OPERATE", StringTools.byte2hex(data));
            try {
                //Socket socket = new Socket(mEtIp.getText().toString(), Integer.parseInt(mEtPort.getText().toString()));
                DoSharedPreferences sp = new DoSharedPreferences();
                Socket socket = new Socket(sp.getIp(OperateActivity.this), sp.getPort(OperateActivity.this));
                OutputStream os = socket.getOutputStream();
                os.write(data);
                os.flush();
                socket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                initData();
            }
            //Toast.makeText(OperateActivity.this, "send success", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 用于发送mqtt请求
     */
    class MyMqttThread extends Thread {
        private MqttClient client;
        private String username = "guest";
        private String password = "guest";
        private String broker = "ws://dev.kingsilk.net:15675/ws";

        private String topic;

        public MyMqttThread(String topic) {
            this.topic = topic;
        }

        public MqttClient startMqtt() {
            Log.d("START", "START");
            if (client != null && client.isConnected()) {
                return client;
            }
            Log.d("START", "NEW START");
            String clientId = String.valueOf(System.currentTimeMillis());
            MemoryPersistence persistence = new MemoryPersistence();
            try {
                client = new MqttClient(broker, clientId, persistence);
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                connOpts.setUserName(username);
                connOpts.setPassword(password.toCharArray());
                // 设置超时时间 单位为秒
                //connOpts.setConnectionTimeout(10);
                // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
                //connOpts.setKeepAliveInterval(20);
                client.connect(connOpts);
                // 初始化数据
                client.subscribe("dev/status/+");
                //client.subscribe("dev/cmd/+");
                //this.mqttSubscribeContent();
                this.callBack();
            } catch (MqttException me) {
                me.printStackTrace();
            }
            return client;
        }

        public void callBack() {
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    try {
                        startMqtt();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    recMsg = StringTools.byte2hex(message.getPayload());
                    //Log.i("NEW_MSG", "订阅:" + recMsg);
                    mainhandler.sendEmptyMessage(0x100);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }
            });
        }

        @Override
        public void run() {
            //super.run();
            startMqtt();
            MqttMessage message = new MqttMessage(data);
            try {
                client.publish(topic, message);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                initData();
            }
            //client.disconnect();
        }
    }

    /**
     * 消息处理
     */
    private Handler mainhandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0x100) {
                mTvShowReceive.setText(recMsg);
            }
        }
    };
}
