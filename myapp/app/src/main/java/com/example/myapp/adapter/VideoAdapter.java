package com.example.myapp.adapter;

import android.content.Context;
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
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.example.myapp.R;
import com.example.myapp.api.Api;
import com.example.myapp.api.ApiConfig;
import com.example.myapp.api.TtitCallback;
import com.example.myapp.entity.BaseResponse;
import com.example.myapp.entity.VideoEntity;
import com.example.myapp.listener.OnItemChildClickListener;
import com.example.myapp.listener.OnItemClickListener;
import com.example.myapp.view.CircleTransform;

import java.util.HashMap;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<VideoEntity> datas;

    public void setDatas(List<VideoEntity> datas) {
        this.datas = datas;
    }

    private OnItemChildClickListener mOnItemChildClickListener;

    private OnItemClickListener mOnItemClickListener;

    public VideoAdapter(Context context) {
        this.mContext = context;
    }

    public VideoAdapter(Context context, List<VideoEntity> datas) {
        this.mContext = context;
        this.datas = datas;
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
        VideoEntity videoEntity = datas.get(position);
        vh.tvTitle.setText(videoEntity.getVtitle());
        vh.tvAuthor.setText(videoEntity.getAuthor());
        if (videoEntity.getVideoSocialEntity() != null) {
            int likenum = videoEntity.getVideoSocialEntity().getLikenum();
            int commentnum = videoEntity.getVideoSocialEntity().getCommentnum();
            int collectnum = videoEntity.getVideoSocialEntity().getCollectnum();
            boolean flagLike = videoEntity.getVideoSocialEntity().isFlagLike();
            boolean flagCollect = videoEntity.getVideoSocialEntity().isFlagCollect();
            if (flagLike) {
                vh.tvLike.setTextColor(Color.parseColor("#677fad"));
                vh.imgLike.setImageResource(R.mipmap.like_selected);
            }
            if (flagCollect) {
                vh.tvCollect.setTextColor(Color.parseColor("#677fad"));
                vh.imgCollect.setImageResource(R.mipmap.collect_selected);
            }
            vh.tvLike.setText(String.valueOf(likenum));
            vh.tvComment.setText(String.valueOf(commentnum));
            vh.tvCollect.setText(String.valueOf(collectnum));
            vh.flagCollect = flagCollect;
            vh.flagLike = flagLike;

        }
        //异步加载图片
        Picasso.with(mContext)
                .load(videoEntity.getHeadurl())
                .transform(new CircleTransform())
                .into(vh.imgHeader);

        Picasso.with(mContext)
                .load(videoEntity.getCoverurl())
                .into(vh.mThumb);
        vh.mPosition = position;
    }

    @Override
    public int getItemCount() {
        if (datas != null && datas.size() > 0) {
            return datas.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvAuthor, tvLike, tvComment, tvCollect;
        private ImageView imgHeader, imgCollect, imgLike, imgComment;
        public ImageView mThumb;
        public PrepareView mPrepareView;
        public FrameLayout mPlayerContainer;
        public int mPosition;
        private boolean flagCollect, flagLike;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvAuthor = view.findViewById(R.id.tv_author);
            tvTitle = view.findViewById(R.id.tv_title);
            tvComment = view.findViewById(R.id.tv_comment);
            tvCollect = view.findViewById(R.id.tv_collect);
            tvLike = view.findViewById(R.id.tv_like);
            imgHeader = view.findViewById(R.id.img_header);
            imgComment = view.findViewById(R.id.img_comment);
            imgCollect = view.findViewById(R.id.img_collect);
            imgLike = view.findViewById(R.id.img_like);
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
                    int collectNum = Integer.parseInt(tvCollect.getText().toString());
                    if (flagCollect) { //已收藏 点击后收藏-1
                        if (collectNum > 0) {
                            tvCollect.setText(String.valueOf(--collectNum));
                            tvCollect.setTextColor(Color.parseColor("#161616"));
                            imgCollect.setImageResource(R.mipmap.collect_unselected);
                            updateCount(datas.get(mPosition).getVid(), 1, !flagCollect);
                        }
                    } else {//未收藏 点击后收藏+1
                        tvCollect.setText(String.valueOf(++collectNum));
                        tvCollect.setTextColor(Color.parseColor("#677fad"));
                        imgCollect.setImageResource(R.mipmap.collect_selected);
                        updateCount(datas.get(mPosition).getVid(), 1, !flagCollect);//数据库更新数据
                    }
                    flagCollect = !flagCollect;//UI更新数据 相当于刷新界面
                }
            });
            imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int likeNum = Integer.parseInt(tvLike.getText().toString());
                    if (flagLike) { //已点赞 点击后点赞 -1
                        if (likeNum > 0) {
                            tvLike.setText(String.valueOf(--likeNum));
                            tvLike.setTextColor(Color.parseColor("#707070"));
                            imgLike.setImageResource(R.mipmap.like_unselected);
                            updateCount(datas.get(mPosition).getVid(), 2, !flagLike);
                        }
                    } else {//未点赞 点击后点赞 +1
                        tvLike.setText(String.valueOf(++likeNum));
                        tvLike.setTextColor(Color.parseColor("#677fad"));
                        imgLike.setImageResource(R.mipmap.like_selected);
                        updateCount(datas.get(mPosition).getVid(), 2, !flagLike);//数据库更新数据
                    }
                    flagLike = !flagLike;//UI更新数据 相当于刷新界面
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

    //更新点赞、收藏数字
    private void updateCount(int vid, int type, boolean flag) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("vid", vid);
        params.put("type", type);
        params.put("flag", flag);
        Api.config(ApiConfig.VIDEO_UPDATE_COUNT, params).postRequest(mContext, new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                Log.e("onSuccess", res);
                Gson gson = new Gson();
                BaseResponse baseResponse = gson.fromJson(res, BaseResponse.class);
                if (baseResponse.getCode() == 0) {

                }
            }

            @Override
            public void onFailure(Exception e) {

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
