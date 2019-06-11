package com.example.mmmjh.calculator;
import android.os.Build;
import android.os.Bundle;
import java.util.Random;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.AlertDialog;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
;
public class CalculatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.viewObj);
        RelativeLayout relativeLayout = new RelativeLayout(this);

        Intent inten = getIntent();
        Bundle bundle = inten.getExtras();
        String tishu = bundle.getString("tishu");//接收传过来的题目的数量

        String min = bundle.getString("minute");
        String sec = bundle.getString("second");//接收时间

        int i1 = 0, minute = 0, second = 0;
        try {
            i1 = Integer.parseInt(tishu);//i1在这里是题目数量的整形值
            minute = Integer.parseInt(min);
            second = Integer.parseInt(sec);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        final EditText[] input = new EditText[i1];
        TextView[] showti = new TextView[i1];
        final int[] shu1 = new int[i1];
        final int[] shu2 = new int[i1];
        final int[] result = new int[i1];
        final String[] user = new String[i1];
        final String[] show = new String[i1];
        LinearLayout ll = (LinearLayout) findViewById(R.id.viewObj);

        Random r = new Random();
        char[] ch = {'+', '-', '×', '÷'}; //字符数组

        final Chronometer ch1 = new Chronometer(this);
        ch1.setBase(SystemClock.elapsedRealtime());
        ch1.setFormat("已用时间：%s");
        ch1.start();
        ll.addView(ch1);
        for (int i = 0; i < i1; i++) {
            int index = r.nextInt(ch.length); //随机数，小于数组的长度数, 0~3
            char flag = ch[index];//获取运算符号
            String d = String.valueOf(flag);//运算符
            show[i] = "";
            int a = (int) (Math.random() * 100);
            int b = (int) (Math.random() * 100);

            shu1[i] = a;
            shu2[i] = b;

            while ((d.equals("+") && (a + b > 100)) || (d.equals("-") && (a - b <= 0)) || (d.equals("×") && (a > 9 || a < 1 || b > 9 || b < 1)) || (d.equals("÷") && (b==0||a / b > 9 || a % b != 0 || a > 81 || b > 9 || a < b))) {
                a = (int) (Math.random() * 100);
                b = (int) (Math.random() * 100);
            }

            if (d.equals("+"))
                result[i] = a + b;
            else if (d.equals("-"))
                result[i] = a - b;
            else if (d.equals("×"))
                result[i] = a * b;
            else
                result[i] = a / b;
            String sa = Integer.toString(a);
            String sb = Integer.toString(b);
            showti[i] = new TextView(this);
            show[i] += "\n" + sa + d + sb + "=";
            showti[i].setTextSize(20);
            showti[i].setText(show[i]);
            showti[i].setId(View.generateViewId());
            input[i] = new EditText(this);
            input[i].setTextSize(25);
            input[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            input[i].setId(View.generateViewId());
            input[i].setEms(6);
            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            if(i == 0)
            {
                layoutParams.setMargins(250,0,0,0);
                showti[i].setLayoutParams(layoutParams);
                relativeLayout.addView(showti[i]);
            }
            else {
                layoutParams.addRule(RelativeLayout.BELOW,showti[i-1].getId());
                layoutParams.setMargins(250,0,0,0);
                showti[i].setLayoutParams(layoutParams);
                relativeLayout.addView(showti[i]);
            }

            layoutParams =
                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.RIGHT_OF,showti[i].getId());
            if(i != 0)layoutParams.addRule(RelativeLayout.BELOW,showti[i-1].getId());
            input[i].setLayoutParams(layoutParams);
            relativeLayout.addView(input[i]);
        }
        linearLayout.addView(relativeLayout);
        final Button finish = new Button(this);
        finish.setText("完成");
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        finish.setLayoutParams(layoutParams);
        linearLayout.addView(finish);

        final int timeover = minute * 60 + second;

        ch1.setOnChronometerTickListener(new OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer arg0) {
                // TODO Auto-generated method stub
                if(SystemClock.elapsedRealtime()-ch1.getBase()>=timeover*1000){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(CalculatorActivity.this)
                            .setMessage("时间到！")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    for (int i = 0; i < shu1.length; i++) {
                                        if (TextUtils.isEmpty(input[i].getText())) {
                                            user[i] = "未作答";
                                        } else {
                                            user[i] = input[i].getText().toString();
                                        }
                                    }
                                    String time = ch1.getText().toString();
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    bundle.putIntArray("shu1", shu1);//产生的数字
                                    bundle.putIntArray("shu2", shu2);//产生的数字
                                    bundle.putStringArray("user", user);//算的结果
                                    bundle.putStringArray("show", show);//产生的算式
                                    bundle.putIntArray("result", result);//正确的结果
                                    bundle.putString("time", time);//使用的时间
                                    intent.putExtras(bundle);
                                    intent.setClass(CalculatorActivity.this, resultActivity.class);//跳转
                                    startActivity(intent);
                                //    CalculatorActivity.this.finish();
                                    dialog.dismiss();
                                }
                            });

                    builder1.create().show();

                    finish.callOnClick();
                }

            }
        });

        finish.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {//触及按钮事件完成页面跳转  进入答案页面
                // TODO Auto-generated method stub

                    for (int i = 0; i < user.length; i++) {
                        if (TextUtils.isEmpty(input[i].getText())) {
                            user[i] = "未作答";
                        } else {
                            user[i] = input[i].getText().toString();
                        }
                    }
                ch1.stop();
                String time = ch1.getText().toString();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putIntArray("shu1", shu1);//产生的数字
                bundle.putIntArray("shu2", shu2);//产生的数字
                bundle.putStringArray("user", user);//算的结果
                bundle.putStringArray("show", show);//产生的算式
                bundle.putIntArray("result", result);//正确的结果
                bundle.putString("time", time);//使用的时间
                intent.putExtras(bundle);
                if(SystemClock.elapsedRealtime()-ch1.getBase()<timeover*1000){
                intent.setClass(CalculatorActivity.this, resultActivity.class);//跳转
                startActivity(intent);
              //  CalculatorActivity.this.finish();//注释了这句话按手机上的返回键可以返回上一层
                    }
            }
        });
    }
}

