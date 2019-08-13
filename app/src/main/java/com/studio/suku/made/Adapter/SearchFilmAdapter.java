package com.studio.suku.made.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.suku.made.Model.SearchFilmResults;
import com.studio.suku.made.R;

import java.util.List;

public class SearchFilmAdapter extends RecyclerView.Adapter<SearchFilmAdapter.FilmViewHolder> {

    public interface OnSearchItemClickListener {
        void onItemClick(SearchFilmResults item);
    }

    private final Context context;
    private final List<SearchFilmResults.ResultsBean> list;
    private OnSearchItemClickListener listener;

    public SearchFilmAdapter(Context context, List<SearchFilmResults.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_search, viewGroup, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder filmViewHolder, int i) {
       filmViewHolder.bind(list.get(i), listener);
       filmViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        final TextView title;
        final TextView rate;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.poster_search_film);
            title = itemView.findViewById(R.id.title_search_film);
            rate = itemView.findViewById(R.id.rate_search_film);
        }

        void bind(final SearchFilmResults.ResultsBean item, final OnSearchItemClickListener listener){
            String path_img = "https://image.tmdb.org/t/p/w500/"+ item.getPoster_path();
            Picasso.get().load(path_img).into(imageView);
            title.setText(item.getOriginal_title());
            rate.setText("Rate : " + item.getVote_average());
        }
    }
}
