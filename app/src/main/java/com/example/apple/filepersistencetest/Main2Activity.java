package com.example.apple.filepersistencetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    // 记录当前的时间
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DatePicker date = (DatePicker) findViewById(R.id.datePicker);
        TimePicker time = (TimePicker) findViewById(R.id.timePicker);
        // 或许当前的年月日，小时，分钟
        Calendar ca = Calendar.getInstance();
        year = ca.get(Calendar.YEAR);
        month = ca.get(Calendar.MONTH);
        day = ca.get(Calendar.DAY_OF_MONTH);
        hour = ca.get(Calendar.HOUR);
        minute = ca.get(Calendar.MINUTE);

        // 初始化DatePicker
        date.init(year, month, day, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker arg0, int year, int month,
                                      int day) {
                Main2Activity.this.year = year;
                Main2Activity.this.month = month;
                Main2Activity.this.day = day;
                // 显示当前时间和日期
                showDate(year, month, day, hour, minute);
            }

        });

        // 为TimerPicker指定事件监听器
        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker arg0, int hour, int minute) {
                Main2Activity.this.hour = hour;
                Main2Activity.this.minute = minute;
            }
        });
    }

    protected void showDate(int year2, int month2, int day2, int hour2,
                            int minute2) {
        EditText text = (EditText) findViewById(R.id.show);
        text.setText("您的购买时间为：" + year2 + "年" + month2 + "月" + day2 + "日"
                + hour2 + "时" + minute2 + "分");
    }

}
