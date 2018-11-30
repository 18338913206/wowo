package com.example.rw17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv=findViewById(R.id.show_data);
        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.post).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get:
                getMethod();
                break;
            case R.id.post:
                postParams();
//                postFile();
                break;
        }
    }

    private void postFile() {
        OkHttpClient client=new OkHttpClient();
        MediaType mediaType=MediaType.parse("text/x-markdown;charset=utf-8");
        File file=new File("readme.md");
        Request request=new Request.Builder()
                .url("https://api.github.com/markdown/raw")
//                .post(RequestBody.create(MEDIA,file))
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("ABC","postFile请求失败");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"posstFile请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("");
                        tv.setText(string);
                    }
                });
            }
        });
    }

    private void postParams() {
        OkHttpClient client=new OkHttpClient();
        FormBody formBody=new FormBody.Builder()
                .add("username","initObject")
                .add("password","initObject")
                .build();
        Request request=new Request.Builder()
                .url("http://www.jianshu.com/")
                .post(formBody)
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.i("ABC","post请求失败");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"posst请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    String string = response.body().string();
                    @Override
                    public void run() {
                        try {
                            tv.setText("");

                            tv.setText("获取的数据为："+string);
                            Toast.makeText(MainActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.i("ABC","失败");
                        }
                    }
                });
            }
        });
    }

    public void getMethod() {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .get()
                .url("http://v.juhe.cn/toutiao/index?type=keji&key=807f10d5da886d846acdde0d7c34024d")
                .build();
        Call call=client.newCall(request);
       call.enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                   }
               });

               Log.i("ABC","get请求失败");
           }

           @Override
           public void onResponse(Call call, final Response response) throws IOException {
               final String string = response.body().string();
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           Toast.makeText(MainActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                           tv.setText("获取的数据为："+string);
                       } catch (Exception e) {
                           e.printStackTrace();
                           Log.i("ABC","失败");
                       }
                   }
               });
           }
       });
    }


}
