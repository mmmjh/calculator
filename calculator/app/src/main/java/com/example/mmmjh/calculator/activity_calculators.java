package com.example.mmmjh.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Intent;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.Toast;

public class activity_calculators extends AppCompatActivity {
    private Button begin;
    EditText b,min,sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculators);
        begin=(Button)findViewById(R.id.button1);

         b=(EditText)findViewById(R.id.editText2);
         min=(EditText)findViewById(R.id.editText3);
         sec=(EditText)findViewById(R.id.editText4);
        begin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent=new Intent();
                String tishu=b.getText().toString();//获取输入的数值
                String minute=min.getText().toString();//获取输入的数值
                String second=sec.getText().toString();//获取输入的数值
                Bundle bundle = new Bundle();
                bundle.putString("tishu", tishu);
                bundle.putString("minute", minute);
                bundle.putString("second", second);
                intent.putExtras(bundle);//将题目数量传入下一个页面 这是一个方法
                if (second.length() == 0 || minute.length() == 0 ) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(activity_calculators.this)
                            .setMessage("设置有误，请完善！")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {//弹出窗口

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    dialog.dismiss();
                                }
                            });
                    builder1.create().show();
                }
                if (second.length() != 0 &&minute.length() != 0 ) {
                    intent.setClass(activity_calculators.this, CalculatorActivity.class);//跳转
                    startActivity(intent);
                    //    activity_calculators.this.finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE, Menu.FIRST + 2, 2, "保存").setIcon(android.R.drawable.ic_menu_edit);
        menu.add(Menu.NONE, Menu.FIRST + 3, 6, "帮助").setIcon(android.R.drawable.ic_menu_help);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST + 2:
                Toast.makeText(this, "保存菜单被点击了", Toast.LENGTH_LONG).show();
                break;
            case Menu.FIRST + 3:
                Toast.makeText(this, "帮助菜单被点击了", Toast.LENGTH_LONG).show();

                break;
        }
        return false;
    }
}
