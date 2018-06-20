package com.example.admin.appmodel;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.modela.view.OneFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String ONEACTIVITY = "/one/activity";
    private static final String TWOACTIVITY = "/two/activity";
    private static final String ONEFRAGMENT = "/onefragment/fragmrnt";
    @BindView(R.id.buta)
    Button buta;
    @BindView(R.id.butb)
    Button butb;
    @BindView(R.id.butc)
    Button butc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.buta, R.id.butb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buta:
                ARouter.getInstance().build(ONEACTIVITY).navigation();
                break;
            case R.id.butb:
                ARouter.getInstance().build(TWOACTIVITY).navigation();
                break;
            case R.id.butc:
                break;
        }
    }
}
