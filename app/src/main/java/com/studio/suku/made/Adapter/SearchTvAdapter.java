package com.studio.suku.made.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.suku.made.Model.SearchTvResults;
import com.studio.suku.made.R;

import java.util.List;

public class SearchTvAdapter extends RecyclerView.Adapter<SearchTvAdapter.TvHolder> {

    private final Context context;
    private final List<SearchTvResults.ResultsBean> resultsBeans;

    public SearchTvAdapter(Context context, List<SearchTvResults.ResultsBean> resultsBeans) {
        this.context = context;
        this.resultsBeans = resultsBeans;
    }

    @NonNull
    @Override
    public SearchTvAdapter.TvHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_search, viewGroup, false);
        return new TvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTvAdapter.TvHolder tvHolder, int i) {
        tvHolder.rate.setText(resultsBeans.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }

    public class TvHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView rate;

        public TvHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.poster_search_film);
            title = itemView.findViewById(R.id.title_search_film);
            rate = itemView.findViewById(R.id.rate_search_film);
        }
    }
}
