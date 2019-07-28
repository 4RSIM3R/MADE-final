package com.studio.suku.made;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.studio.suku.made.LocalDb.Contract;
import com.studio.suku.made.LocalDb.Helper;
import com.studio.suku.made.LocalDb.LocalAdapter;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView favorite;
    SQLiteDatabase sqLiteDatabase;
    public static String Type = "EXTRA_TYPE";
    private String type_lits;
    LocalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Helper helper = new Helper(this);
        sqLiteDatabase = helper.getReadableDatabase();
        type_lits = getIntent().getStringExtra(Type);

        Log.d("Kiriman", type_lits);

        favorite = findViewById(R.id.list_favorite);
        favorite.setHasFixedSize(true);
        favorite.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LocalAdapter(this, getAllItems());
        favorite.setAdapter(adapter);
        adapter.swapCursor(getAllItems());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                deleteItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(favorite);

    }

    private void deleteItem(long id){
        sqLiteDatabase.delete(Contract.Entry.TABLE_NAME,
                Contract.Entry._ID + " = " + id, null);
        adapter.swapCursor(getAllItems());

    }

    private Cursor getAllItems(){
        boolean isEmpty = false;
        String selectQuery = "SELECT  * FROM " + Contract.Entry.TABLE_NAME + " WHERE type = ? ";
        return sqLiteDatabase.rawQuery(selectQuery, new String[]{type_lits});

    }
}
