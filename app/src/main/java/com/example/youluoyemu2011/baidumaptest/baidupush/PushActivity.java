package com.example.youluoyemu2011.baidumaptest.baidupush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.example.youluoyemu2011.baidumaptest.R;

public class PushActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        initPush();
    }

    private void initPush() {
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,"6Fk7qemTMKdwsAwzucv0VdEXkYQ5dEfQ");
    }
}
