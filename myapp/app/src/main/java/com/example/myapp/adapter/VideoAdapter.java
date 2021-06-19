package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videocontroller.component.PrepareView;
import com.example.myapp.R;
import com.example.myapp.entity.VideoEntity;
import com.example.myapp.listener.OnItemChildClickListener;
import com.example.myapp.listener.OnItemClickListener;
import com.example.myapp.view.CircleTransform;
import com.example.myapp.view.FixedViewPager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<VideoEntity> datas;
    private ViewGroup parent;
    private int viewType;

    private OnItemChildClickListener mOnItemChildClickListener;
    private OnItemClickListener mOnItemClickListener;

    public void setDatas(List<VideoEntity> datas) {
        this.datas = datas;
    }

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
        ViewHolder viewHolder = (ViewHolder) holder;
        VideoEntity videoEntity = datas.get(position);
        viewHolder.tvAuthor.setText(videoEntity.getAuthor());
        viewHolder.tvTitle.setText(videoEntity.getVtitle());
        viewHolder.tvComment.setText(String.valueOf(videoEntity.getCommentnum()));
        viewHolder.tvCollect.setText(String.valueOf(videoEntity.getCollectnum()));
        viewHolder.tvLike.setText(String.valueOf(videoEntity.getLikenum()));
//        if (videoEntity.getVideoSocialEntity() != null) {
//            int likenum = videoEntity.getVideoSocialEntity().getLikenum();
//            int commentnum = videoEntity.getVideoSocialEntity().getCommentnum();
//            int collectnum = videoEntity.getVideoSocialEntity().getCollectnum();
//            boolean flagLike = videoEntity.getVideoSocialEntity().isFlagLike();
//            boolean flagCollect = videoEntity.getVideoSocialEntity().isFlagCollect();
//            if (flagLike) {
//                viewHolder.tvLike.setTextColor(Color.parseColor("#E21918"));
//                viewHolder.imgDizan.setImageResource(R.mipmap.dianzan_select);
//            }
//            if (flagCollect) {
//                viewHolder.tvCollect.setTextColor(Color.parseColor("#E21918"));
//                viewHolder.imgCollect.setImageResource(R.mipmap.collect_select);
//            }
//            viewHolder.tvLike.setText(String.valueOf(likenum));
//            viewHolder.tvComment.setText(String.valueOf(commentnum));
//            viewHolder.tvCollect.setText(String.valueOf(collectnum));
//            viewHolder.flagCollect = flagCollect;
//            viewHolder.flagLike = flagLike;
//        }

        //异步加载图片
        Picasso.with(mContext)
                .load(videoEntity.getHeadurl())
                .transform(new CircleTransform())
                .into(viewHolder.imgHeader);
        Picasso.with(mContext)
                .load(videoEntity.getCoverurl())
                .into(viewHolder.mThumb);

        viewHolder.mPosition = position;
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
        private TextView tvAuthor, tvTitle, tvComment, tvCollect, tvLike;
        private ImageView imgHeader;

        public FrameLayout mPlayerContainer;
        public PrepareView mPrepareView;
        public ImageView mThumb;
        public int mPosition;

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

        public ViewHolder(@NonNull View view) {
            super(view);
            tvAuthor = view.findViewById(R.id.tv_author);
            tvTitle = view.findViewById(R.id.tv_title);
            tvComment = view.findViewById(R.id.tv_comment);
            tvCollect = view.findViewById(R.id.tv_collect);
            tvLike = view.findViewById(R.id.tv_like);
            imgHeader = view.findViewById(R.id.img_header);
            mPlayerContainer = view.findViewById(R.id.player_container);
            mPrepareView = view.findViewById(R.id.prepare_view);
            mThumb = mPrepareView.findViewById(R.id.thumb);
            if (mOnItemChildClickListener != null) {
                mPlayerContainer.setOnClickListener(this);
            }
            if (mOnItemClickListener != null) {
                view.setOnClickListener(this);
            }

            //通过tag将ViewHolder和itemView绑定
            view.setTag(this);
        }
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
