package com.example.rw17;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private ViewPager vp;
    private PagerTabStrip pts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
    }

    private void initView() {
        vp=findViewById(R.id.vp);
        pts=findViewById(R.id.pts);
        RVFragment rvFragment=new RVFragment();
        ListFragment listFragment=new ListFragment();
        List<Fragment> list=new ArrayList<>();
        list.add(listFragment);
        list.add(rvFragment);
        List<String> titleList=new ArrayList<>();
        titleList.add("科技");
        titleList.add("军事");
        MyVPAdapter adapter=new MyVPAdapter(getSupportFragmentManager(),list,titleList);
        vp.setAdapter(adapter);
    }
}
