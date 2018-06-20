package com.example.modelb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.common.Bean.MyBean;
import com.example.modelb.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by admin on 2018/4/2.
 */

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    private MyItemClickListener myItemClickListener;

    public RecAdapter(ArrayList<MyBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    private ArrayList<MyBean.ResultsBean> list;
    private Context context;
    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lists, parent, false);
        ViewHolder viewHolder  = new ViewHolder(inflate);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final RecAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getUrl()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            myItemClickListener.onItemClick(list.get(position).getUrl(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public   ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image1);
        }
    }

    public interface MyItemClickListener {
        void onItemClick(String imageurl, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *
     * @param myItemClickListener
     */
    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }
}

