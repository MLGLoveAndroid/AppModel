package com.example.modelb.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.modelb.Adapter.EnvetImage;
import com.example.modelb.R;
import com.example.modelb.R2;
import com.github.chrisbanes.photoview.PhotoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/Image/activity")
public class ImageActivity extends AppCompatActivity {

    @BindView(R2.id.photoview)
    PhotoView photoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true )
    public void onEnvetImage(EnvetImage event) {
        Toast.makeText(ImageActivity.this, event.getImage(), Toast.LENGTH_SHORT).show();
        Glide.with(ImageActivity.this).load(event.getImage()).into(photoview);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ImageActivity.this);
    }

}
