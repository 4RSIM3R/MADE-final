package com.studio.suku.made.LocalDb;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studio.suku.made.R;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {

    private ArrayList<Favorite> listFavorite = new ArrayList<>();
    private Activity activity;

    public FavoriteAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListFavorite(ArrayList<Favorite> listFavorite) {
        if (listFavorite.size() > 0){
            this.listFavorite.clear();
        }
        this.listFavorite = listFavorite;
        notifyDataSetChanged();
    }

    public ArrayList<Favorite> getListFavorite(){
        return listFavorite;
    }

    public void addItem(Favorite favorite){
        this.listFavorite.add(favorite);
        notifyItemInserted(listFavorite.size() - 1);
    }

    public void removeItem(int position){
        this.listFavorite.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,listFavorite.size());
    }


    @NonNull
    @Override
    public FavoriteAdapter.FavoriteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_search, viewGroup, false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteHolder favoriteHolder, int i) {
            favoriteHolder.textView.setText(listFavorite.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return listFavorite.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_search_film);
        }
    }
}
