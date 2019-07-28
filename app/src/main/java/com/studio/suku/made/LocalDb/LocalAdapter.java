package com.studio.suku.made.LocalDb;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.suku.made.R;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.LocalViewHolder> {

    private final Context context;
    private Cursor mcursor;

    public LocalAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.mcursor = cursor;
    }


    @NonNull
    @Override
    public LocalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_search, viewGroup, false);
        return new LocalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalViewHolder localViewHolder, int i) {

        if (!mcursor.moveToPosition(i)){
            return;
        }

        String title = mcursor.getString(mcursor.getColumnIndex(Contract.Entry.COLUMN_NAME));
        String img_path = mcursor.getString(mcursor.getColumnIndex(Contract.Entry.COLUMN_IMAGE));
        String rate = mcursor.getString(mcursor.getColumnIndex(Contract.Entry.COLUMN_RATE));
        long id = mcursor.getLong(mcursor.getColumnIndex(Contract.Entry._ID));

        localViewHolder.title.setText(title);
        Picasso.get().load(img_path).into(localViewHolder.imageView);
        localViewHolder.rate.setText(rate);
        localViewHolder.itemView.setTag(id);
    }

    public void swapCursor(Cursor newCursor){
        if (mcursor != null){
            mcursor.close();
        }

        mcursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    public class LocalViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView rate;
        public LocalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.poster_search_film);
            title = itemView.findViewById(R.id.title_search_film);
            rate = itemView.findViewById(R.id.rate_search_film);
        }
    }
}
