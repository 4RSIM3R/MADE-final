package com.studio.suku.made.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.studio.suku.made.DetailTvActivity;
import com.studio.suku.made.Model.TvResults;
import com.studio.suku.made.R;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    final Context context;
    final List<TvResults.ResultsBean> list;

    public TvAdapter(Context context, List<TvResults.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_row, viewGroup, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder tvViewHolder, int i) {
        final int position = tvViewHolder.getAdapterPosition();
        String path_img = "https://image.tmdb.org/t/p/w500/"+ list.get(i).getPoster_path();
        Picasso.get().load(path_img).into(tvViewHolder.imageView);
        tvViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTvActivity.class);
                TvResults.ResultsBean resultsBean = new TvResults.ResultsBean();

                resultsBean.setOriginal_name(list.get(position).getOriginal_name());
                resultsBean.setPoster_path("https://image.tmdb.org/t/p/w500/" + list.get(position).getPoster_path());
                resultsBean.setOverview(list.get(position).getOverview());
                resultsBean.setVote_average(list.get(position).getVote_average());
                intent.putExtra(DetailTvActivity.EXTRA_DATA, resultsBean);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
