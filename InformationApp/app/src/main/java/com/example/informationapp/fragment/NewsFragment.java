package com.example.informationapp.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.informationapp.R;
import com.example.informationapp.adapter.NewsAdapter;
import com.example.informationapp.entity.NewsEntity;
import com.example.informationapp.entity.NewsListResponse;
import com.example.informationapp.entity.NewsTab;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends BaseFragment {
    private String URL;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NewsAdapter mNewsAdapter;
    private List<NewsEntity> mDatas = new ArrayList<>();

    public NewsFragment() {
    }

    public static NewsFragment newInstance(String URL) {
        NewsFragment fragment = new NewsFragment();
        fragment.URL = URL;
        return fragment;
    }

    @Override
    protected void initView() {
        mRecyclerView = mRootView.findViewById(R.id.news_recycler_view);

        //获得传递过来的数据
        Bundle bundle = getArguments();
        NewsTab newsTab = (NewsTab) bundle.getSerializable("type");
        URL = newsTab.getUrl();
    }

    @Override
    protected void initData() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mNewsAdapter = new NewsAdapter(getActivity(), mDatas);
        mRecyclerView.setAdapter(mNewsAdapter);
        try {
            refresh();
        } catch (IOException e) {
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_news;
    }

    private void refresh() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String resultJson = response.body().string();
                NewsListResponse mNewsListResponse = new Gson().fromJson(resultJson, NewsListResponse.class);
                if (mNewsListResponse != null) {
                    if (mNewsListResponse.getErrorCode() == 0) {
                        mDatas.clear();
                        mDatas.addAll(mNewsListResponse.getResult().getData());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mNewsAdapter.notifyDataSetChanged();
                            }
                        });
                    } else if (mNewsListResponse.getErrorCode() == 10011 || mNewsListResponse.getErrorCode() == 10012 || mNewsListResponse.getErrorCode() == 10013) {
                        showToastSync("资讯请求次数超过限制");
                    } else {
                        showToastSync("请求错误");
                    }
                }
            }
        });
    }


}
