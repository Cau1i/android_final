package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.entity.VideoEntity;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<VideoEntity> datas;
    private ViewGroup parent;
    private int viewType;

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
        ViewHolder viewHolder = (ViewHolder) holder;
        VideoEntity videoEntity = datas.get(position);
        viewHolder.tvAuthor.setText(videoEntity.getAuthor());
        viewHolder.tvTitle.setText(videoEntity.getTitle());
        viewHolder.tvComment.setText(String.valueOf(videoEntity.getCommentCount()));
        viewHolder.tvCollect.setText(String.valueOf(videoEntity.getCollectCount()));
        viewHolder.tvLike.setText(String.valueOf(videoEntity.getLikeCount()));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAuthor, tvTitle, tvComment, tvCollect, tvLike;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvAuthor = view.findViewById(R.id.tv_author);
            tvTitle = view.findViewById(R.id.tv_title);
            tvComment = view.findViewById(R.id.tv_comment);
            tvCollect = view.findViewById(R.id.tv_collect);
            tvLike = view.findViewById(R.id.tv_like);
        }
    }
}
