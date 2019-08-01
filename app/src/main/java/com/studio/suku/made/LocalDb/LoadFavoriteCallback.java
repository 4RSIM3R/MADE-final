package com.studio.suku.made.LocalDb;

import java.util.ArrayList;

public interface LoadFavoriteCallback {

    void preExecute();
    void postExecute(ArrayList<Favorite> favoriteList);

}
