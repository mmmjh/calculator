package com.example.mmmjh.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class resultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView text=(TextView)findViewById(R.id.textresult);
        TextView text2=(TextView)findViewById(R.id.textView1);
        Intent inten=getIntent();
        Bundle bundle=inten.getExtras();
        int []shu1 = inten.getIntArrayExtra("shu1");//产生的数字
        int []shu2 = inten.getIntArrayExtra("shu2");//产生的数字
        String []user = inten.getStringArrayExtra("user");//算的结果
        String []show = inten.getStringArrayExtra("show");//产生的算式
        int []result = inten.getIntArrayExtra("result");//正确的结果
        String time=bundle.getString("time");//用时
        int score=0;
        String select="";
        String all="                ";
        for(int i=0;i<shu1.length;i++)
        {
            select="";
            String resultstring="";
            resultstring=Integer.toString(result[i]);
            if(user[i].equals(resultstring))
            {
                score++;//答对的数目
                select+="✔☺";
            }
            else
                select+="✘☹";
            all+=""+show[i]+user[i]+"                             "+result[i]+"                             "+select+"\n";
            text.setText(all);
        }
        text2.setText("本次一共"+shu1.length+"道题，回答正确"+score+"道！"+time);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE, Menu.FIRST + 2, 2, "返回设置页面").setIcon(android.R.drawable.ic_menu_edit);
        menu.add(Menu.NONE, Menu.FIRST + 3, 6, "帮助").setIcon(android.R.drawable.ic_menu_help);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST + 2:
                Intent intent = new Intent();
                intent.setClass(resultActivity.this, activity_calculators.class);//跳转
                startActivity(intent);
                break;
            case Menu.FIRST + 3:
                Toast.makeText(this, "帮助菜单被点击了", Toast.LENGTH_LONG).show();

                break;
        }
        return false;
    }
}
