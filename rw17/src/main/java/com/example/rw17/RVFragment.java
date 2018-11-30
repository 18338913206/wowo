package com.example.rw17;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RVFragment extends Fragment {
    private RecyclerView rv;
    List<String> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_rv, container, false);
        rv=view.findViewById(R.id.rv);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        list=new ArrayList<>();
        final MyRVAdapter adapter=new MyRVAdapter(list,getActivity());
        rv.setAdapter(adapter);
        OkHttpClient client=new OkHttpClient();
        final Request request=new Request.Builder()
                .url(Constant.JUNSHI_URL)
                .method("GET",null)
                .build();

        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("ABC","加载军事新闻失败");
               // Toast.makeText(getActivity(),"加载军事新闻失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Log.i("ABC",s);
                JXString(s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"数据加载成功",Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });
        return view;
    }

    private void JXString(String string) {
        try {
            JSONObject object=new JSONObject(string);
            JSONArray data = object.getJSONObject("result").getJSONArray("data");
            for(int i=0;i<data.length();i++){
                JSONObject jsonObject = data.getJSONObject(i);
                String imgUrl=jsonObject.getString("thumbnail_pic_s");
                list.add(imgUrl);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
