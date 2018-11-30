package com.example.shipin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {
    //视频
    private VideoView video_view;
    //开始  停止  暂停
    private Button video_start, video_pause, video_replay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        initView();

        if (ContextCompat.checkSelfPermission(VideoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(VideoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPath();//初始化MediaPlayer
        }
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(), "a.mp4");
        video_view.setVideoPath(file.getPath()); //指定视频文件的路径
    }

    //初始化view
    private void initView() {
        video_view = (VideoView) findViewById(R.id.video_view);
        video_start = (Button) findViewById(R.id.video_start);
        video_start.setOnClickListener(this);
        video_pause = (Button) findViewById(R.id.video_pause);
        video_pause.setOnClickListener(this);
        video_replay = (Button) findViewById(R.id.video_replay);
        video_replay.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_start:
                video_view.start(); //开始播放
                break;
            case R.id.video_pause:
                video_view.pause(); //暂停播放
                break;
            case R.id.video_replay:
                video_view.resume(); //重新播放
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        if (video_view !=null){
            video_view.suspend();
        }
    }
}