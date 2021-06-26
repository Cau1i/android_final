package com.example.informationapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.informationapp.R;
import com.example.informationapp.activity.DetailActivity;
import com.example.informationapp.entity.NewsEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<NewsEntity> mDatas;

    public NewsAdapter(Context context, List<NewsEntity> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_one, parent, false);
            return new ViewHolderOne(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_two, parent, false);
            return new ViewHolderThree(view);
        }
    }

    //实现多种类型的view
    @Override
    public int getItemViewType(int position) {
        String thum = mDatas.get(position).getThumbnail_pic_s02();
        int type = 1;
        if (thum != null) {
            type = 3;
        }
        return type;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        NewsEntity newsEntity = mDatas.get(position);
        if (type == 1) {
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.newsTitle.setText(newsEntity.getTitle());
            viewHolderOne.newsAuthor.setText(newsEntity.getAuthor_name());
            Picasso.with(mContext).load(newsEntity.getThumbnail_pic_s()).into(viewHolderOne.newsImage);
            viewHolderOne.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("url", newsEntity.getUrl());
                    mContext.startActivity(intent);
                }
            });
        } else {
            ViewHolderThree viewHolderThree = (ViewHolderThree) holder;
            viewHolderThree.newsTitle.setText(newsEntity.getTitle());
            viewHolderThree.newsAuthor.setText(newsEntity.getAuthor_name());
            Picasso.with(mContext).load(newsEntity.getThumbnail_pic_s()).into(viewHolderThree.newsImage1);
            Picasso.with(mContext).load(newsEntity.getThumbnail_pic_s02()).into(viewHolderThree.newsImage2);
            Picasso.with(mContext).load(newsEntity.getThumbnail_pic_s03()).into(viewHolderThree.newsImage3);
            viewHolderThree.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("url", newsEntity.getUrl());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {
        private TextView newsTitle, newsAuthor;
        private ImageView newsImage;

        public ViewHolderOne(@NonNull View view) {
            super(view);
            newsTitle = view.findViewById(R.id.tv_title);
            newsAuthor = view.findViewById(R.id.tv_author);
            newsImage = view.findViewById(R.id.img_news);
        }
    }

    public class ViewHolderThree extends RecyclerView.ViewHolder {
        private TextView newsTitle, newsAuthor;
        private ImageView newsImage1, newsImage2, newsImage3;

        public ViewHolderThree(@NonNull View view) {
            super(view);
            newsTitle = view.findViewById(R.id.tv_title);
            newsAuthor = view.findViewById(R.id.tv_author);
            newsImage1 = view.findViewById(R.id.img_news1);
            newsImage2 = view.findViewById(R.id.img_news2);
            newsImage3 = view.findViewById(R.id.img_news3);
        }
    }
}

