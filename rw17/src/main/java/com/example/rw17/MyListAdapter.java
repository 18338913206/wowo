package com.example.rw17;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private List<News> list;
    private Context context;
    public MyListAdapter(List<News> list,Context context){
        this.list=list;
        this.context=context;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.listview_item_layout,parent,false);
            holder=new ViewHolder();
            holder.iv=convertView.findViewById(R.id.lv_iv);
            holder.cv=convertView.findViewById(R.id.lv_cv);
            holder.tv=convertView.findViewById(R.id.lv_tv);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        News news = list.get(position);
        holder.tv.setText(news.getTitle());
        Glide.with(context)
                .load(news.getImgUrl())
                .into(holder.iv);
        return convertView;
    }

    class ViewHolder {
        ImageView iv;
        CardView cv;
        TextView tv;
    }
}
