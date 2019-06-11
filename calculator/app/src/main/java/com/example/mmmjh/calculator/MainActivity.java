package com.example.mmmjh.calculator;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin = (Button) findViewById(R.id.button1);
        begin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, activity_calculators.class);//跳转
                startActivity(intent);
                //   MainActivity.this.finish();

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

