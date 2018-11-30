package com.example.rw17;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    private ListView lv;
    private List<News> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list, container, false);
        lv=view.findViewById(R.id.lv);
        list=new ArrayList<>();
        final MyListAdapter adapter=new MyListAdapter(list,getActivity());
        lv.setAdapter(adapter);
        OkHttpClient client=new OkHttpClient();
        final Request request=new Request.Builder()
                .url(Constant.KEJI_URL)
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              //  Toast.makeText(getContext(),"获取新闻数据失败",Toast.LENGTH_SHORT).show();
                Log.i("ABC","获取新闻数据失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i("ABC",string);
                JXString(string);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
                String title=jsonObject.getString("title");
                String imgUrl=jsonObject.getString("thumbnail_pic_s");
                list.add(new News(imgUrl,title));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
