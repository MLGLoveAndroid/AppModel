package com.example.modela.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Bean.FunnyBean;
import com.example.modela.R;
import com.example.modela.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/3/28.
 */

public class MyBeanAdapter extends BaseAdapter {
    private ArrayList<FunnyBean.DataBean> list;
    private Context context;

    public MyBeanAdapter(ArrayList<FunnyBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text.setText(list.get(i).getText());
        Glide.with(context).load(list.get(i).getBimageuri()).into(holder.image);
       // Glide.with(context).load(list.get(i).getImage_small()).into(holder.image1);
        //Glide.with(context).load(list.get(i).getCdn_img()).into(holder.image2);
        return view;
    }



    class ViewHolder {
        @BindView(R2.id.image)
        ImageView image;
        @BindView(R2.id.text)
        TextView text;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
