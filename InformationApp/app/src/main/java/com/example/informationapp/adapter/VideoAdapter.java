package com.example.informationapp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videocontroller.component.PrepareView;
import com.example.informationapp.entity.VideoEntity;
import com.example.informationapp.entity.VideoListResponse;
import com.example.informationapp.listener.OnItemChildClickListener;
import com.example.informationapp.listener.OnItemClickListener;
import com.example.informationapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<VideoEntity> mDatas;

    private OnItemChildClickListener mOnItemChildClickListener;
    private OnItemClickListener mOnItemClickListener;

    public VideoAdapter() {
    }

    public VideoAdapter(Context context, List<VideoEntity> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        VideoEntity videoEntity = mDatas.get(position);
        vh.tvTitle.setText(videoEntity.getVtitle());
        vh.tvAuthor.setText(videoEntity.getAuthor());
        if (videoEntity.getVideoSocialEntity() != null) {
            boolean flagCollect = videoEntity.getVideoSocialEntity().isFlagCollect();
            if (flagCollect) {
                vh.imgCollect.setImageResource(R.mipmap.collect_selected);
            }
            vh.flagCollect = flagCollect;
        }
        Picasso.with(mContext)
                .load(videoEntity.getCoverurl())
                .into(vh.mThumb);
    }

    @Override
    public int getItemCount() {
        if (mDatas != null && mDatas.size() > 0) {
            return mDatas.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvAuthor, tvLike, tvComment, tvCollect;
        private ImageView mThumb, imgCollect;
        public PrepareView mPrepareView;
        public FrameLayout mPlayerContainer;
        public int mPosition;
        private boolean flagCollect, flagLike;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvAuthor = view.findViewById(R.id.tv_author);
            tvTitle = view.findViewById(R.id.tv_title);
            imgCollect = view.findViewById(R.id.img_collect);
            mPlayerContainer = view.findViewById(R.id.player_container);
            mPrepareView = view.findViewById(R.id.prepare_view);
            mThumb = mPrepareView.findViewById(R.id.thumb);
            if (mOnItemChildClickListener != null) {
                mPlayerContainer.setOnClickListener(this);
            }
            if (mOnItemClickListener != null) {
                view.setOnClickListener(this);
            }
            imgCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flagCollect) { //已收藏 点击后收藏-1
                        imgCollect.setImageResource(R.mipmap.collect_unselected);
                        updateCount(mDatas.get(mPosition).getVid(), 2, !flagLike);//数据库增加收藏

                    } else {
                        imgCollect.setImageResource(R.mipmap.collect_selected);
                        updateCount(mDatas.get(mPosition).getVid(), 2, !flagLike);//数据库减少收藏
                    }
                    flagCollect = !flagCollect;//UI更新数据 相当于刷新界面
                }
            });
            //通过tag将ViewHolder和itemView绑定
            view.setTag(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.player_container) {
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildClick(mPosition);
                }
            } else {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mPosition);
                }
            }
        }
    }

    //更新收藏
    private void updateCount(int vid, int type, boolean flag) {
        HashMap<String, Object> mParams = new HashMap<String, Object>();
        mParams.put("vid", vid);
        mParams.put("type", type);
        mParams.put("flag", flag);
        SharedPreferences sp = mContext.getSharedPreferences("sp_example", MODE_PRIVATE);
        String token = sp.getString("token", "");
        JSONObject jsonObject = new JSONObject(mParams);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("contentType", "application/json;charset=UTF-8")
                .addHeader("token", token)
                .url("http://47.112.180.188:8080/renren-fast/app/videolist/updateCount")
                .post(requestBodyJson)
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
                VideoListResponse mVideoListResponse = new Gson().fromJson(resultJson, VideoListResponse.class);
            }
        });
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
