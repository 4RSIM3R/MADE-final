package com.studio.suku.made.LocalDb;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.studio.suku.made.R;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {

    private ArrayList<Favorite> listFavorite = new ArrayList<>();
    private FavoriteHelper favoriteHelper;
    private Activity activity;
    private Context context;

    public FavoriteAdapter(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
        favoriteHelper = new FavoriteHelper(context);
        favoriteHelper.open();
    }



    public void setListFavorite(ArrayList<Favorite> listFavorite) {
        if (listFavorite.size() > 0){
            this.listFavorite.clear();
        }
        this.listFavorite.addAll(listFavorite);
        notifyDataSetChanged();
        Log.d("Berhasil", listFavorite.get(0).getName());
    }

    public ArrayList<Favorite> getListFavorite(){
        return listFavorite;
    }

    public void addItem(Favorite favorite){
        this.listFavorite.add(favorite);
        notifyItemInserted(listFavorite.size() - 1);
    }

    private void removeItem(int position){
        this.listFavorite.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,listFavorite.size());
    }


    @NonNull
    @Override
    public FavoriteAdapter.FavoriteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_favorite, viewGroup, false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteHolder favoriteHolder, int i) {
            final int position = favoriteHolder.getAdapterPosition();
            favoriteHolder.textView.setText(listFavorite.get(i).getName());
            final String title = listFavorite.get(i).getName();
            favoriteHolder.itemView.setTag(title);
            favoriteHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Id nya", "ID : " + position);
                    favoriteHelper.deleteFavorite(title);
                    removeItem(position);
                }
            });

    }

    @Override
    public int getItemCount() {
        return listFavorite.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder {

        TextView textView;
        Button button;

        FavoriteHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_favorite);
            button = itemView.findViewById(R.id.delete_favorite);
        }
    }
}
