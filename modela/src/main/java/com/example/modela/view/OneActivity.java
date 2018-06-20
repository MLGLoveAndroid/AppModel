package com.example.modela.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.BaseMVP.BaseMvpActivity;
import com.example.common.Bean.FunnyBean;
import com.example.modela.Adapter.MyBeanAdapter;
import com.example.modela.R;
import com.example.modela.R2;
import com.example.modela.presenter.ModelApresenter;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/one/activity")
public class OneActivity extends BaseMvpActivity<oneView, ModelApresenter> implements oneView{

    @BindView(R2.id.mvp_loading)
    ProgressBar mvp_loading;
    @BindView(R2.id.mvp_listview)
    ListView mvp_listview;
    private FunnyBean myBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public ModelApresenter initPresenter() {
        return new ModelApresenter();
    }

    //@OnClick({R2.id.mvp_loading,R2.id.mvp_listview})
   // public void onClick(View view) {

    //}

    @Override
    public void showLoading() {
        mvp_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mvp_loading.setVisibility(View.GONE);
    }


    @Override
    public void setListItem(Object o) {
        myBean = (FunnyBean) o;
        ArrayList<FunnyBean.DataBean> data = (ArrayList<FunnyBean.DataBean>) myBean.getData();
        MyBeanAdapter myBeanAdapter = new MyBeanAdapter(data, OneActivity.this);
        mvp_listview.setAdapter(myBeanAdapter);
        myBeanAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
