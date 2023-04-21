package com.example.sqlite_th2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPageAdapter extends FragmentStatePagerAdapter {


    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new SongList();
            case 1: return new SongFavorite();
            case 2: return new SongFind();
            default: return new SongFavorite();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
