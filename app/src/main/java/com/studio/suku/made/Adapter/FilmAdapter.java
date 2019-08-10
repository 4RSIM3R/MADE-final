package com.studio.suku.made.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.studio.suku.made.DetailFilmActivity;
import com.studio.suku.made.Model.MoviesResults;
import com.studio.suku.made.R;


import java.util.List;

import static com.studio.suku.made.DetailFilmActivity.*;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder>{

    public interface OnItemClickListener {
        void onItemClick(MoviesResults.ResultsBean item);
    }

    private final Context context;
    private final List<MoviesResults.ResultsBean> list;
    private OnItemClickListener listener;


    public FilmAdapter(Context context, List<MoviesResults.ResultsBean> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_row, viewGroup, false);
        return new FilmHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder filmHolder, int i) {
        final int position = filmHolder.getAdapterPosition();
        filmHolder.bind(list.get(i), listener);
        filmHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailFilmActivity.class);
                MoviesResults.ResultsBean resultsBean = new MoviesResults.ResultsBean();
                resultsBean.setOriginal_title(list.get(position).getOriginal_title());
                resultsBean.setPoster_path("https://image.tmdb.org/t/p/w500/" + list.get(position).getPoster_path());
                resultsBean.setOverview(list.get(position).getOverview());
                resultsBean.setVote_average(list.get(position).getVote_average());
                intent.putExtra(DetailFilmActivity.EXTRA_DATA, resultsBean);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FilmHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;


        FilmHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item_photo);

        }

        void bind(final MoviesResults.ResultsBean item, final OnItemClickListener listener) {
            String path_img = "https://image.tmdb.org/t/p/w500/"+ item.getPoster_path();
            Picasso.get().load(path_img).into(imageView);
         itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }


    }


}
