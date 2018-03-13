package com.example.apple.filepersistencetest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity{

    private EditText edit;
    private EditText edit2;
    private TextView textView2;
    private DatePicker picker;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=(EditText) findViewById(R.id.fileSave);//两个文字输入框都需要编辑
        edit2=(EditText) findViewById(R.id.edit2);//
        textView2 = (TextView) findViewById(R.id.textView2);

        final String inputText=load();//从数据库中取出*****************
        if(!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);//和取得文本相反，这里是输入文本到组件上*******
            edit.setSelection(inputText.length());
            textView2.setText("TextView的试验："+"\n"+inputText);//这里可以空格
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_LONG).show();
        }

        Button button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        Button button=(Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override//点击数据抽取数据写入代码
            public void onClick(View view) {
                edit2.setText(inputText);
                Toast.makeText(MainActivity.this,"estoring succeeded",Toast.LENGTH_LONG).show();
            }
        });
    }

    //随着退出自动调用写入函数操作
    @Override
    protected void onDestroy(){
        super.onDestroy();
        String input=edit.getText().toString();  //这里的语句是组件的取得文本的功能*************
        save(input);                             //引用保存函数****************
    }

    //尝试进行写入文件操作的函数
    public void save(String inputText) {
       //本来是可以在内部，定义文件写入，现在变成函数的接口传入 String data="Data to save 2018.3.2";
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try {
            out=openFileOutput("data", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //尝试进入读出文件操作
    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            in=openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


}
