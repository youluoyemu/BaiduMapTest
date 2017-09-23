package com.example.youluoyemu2011.baidumaptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.youluoyemu2011.baidumaptest.baidumap.LocationActivity;
import com.example.youluoyemu2011.baidumaptest.baidumap.MapActivity;
import com.example.youluoyemu2011.baidumaptest.baidupush.PushActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnLocation, mBtnMap,mBtnPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();

    }

    private void initEvent() {
        mBtnLocation.setOnClickListener(this);
        mBtnMap.setOnClickListener(this);
        mBtnPush.setOnClickListener(this);
    }

    private void initView() {
        mBtnLocation = (Button) findViewById(R.id.btn_location);
        mBtnMap = (Button) findViewById(R.id.btn_map);
        mBtnPush = (Button) findViewById(R.id.btn_push);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_location:{
                Intent intent = new Intent(this, LocationActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_map:{
                Intent intent = new Intent(this, MapActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_push:{
                Intent intent = new Intent(this, PushActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
