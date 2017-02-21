package net.kingsilk.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.NumberPicker;

public class TimeSettingDialog extends AppCompatActivity {

    private NumberPicker mNpHour;
    private NumberPicker mNpMin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_setting_dialog);
        mNpHour = (NumberPicker) findViewById(R.id.np_hour);
        mNpHour.setMinValue(0);
        mNpHour.setMaxValue(3);
        mNpMin = (NumberPicker) findViewById(R.id.np_min);
        mNpMin.setMinValue(0);
        mNpMin.setMaxValue(11);
        String[] mins = new String[]{"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
        mNpMin.setDisplayedValues(mins);
        String[] list = getIntent().getStringExtra("time").split(":");
        mNpHour.setValue(Integer.parseInt(list[0]));
        mNpMin.setValue(Integer.parseInt(list[1]) / 5);
    }

    public void confirm(View view) {
        Intent intent = new Intent();
        intent.putExtra("hour", mNpHour.getValue());
        intent.putExtra("min", mNpMin.getValue() * 5);
        this.setResult(0x200, intent);
        finish();
    }

    public void cancel(View view) {
        Intent intent = new Intent();
        this.setResult(0x404, intent);
        finish();
    }

}
