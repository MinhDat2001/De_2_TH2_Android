package com.example.sqlite_th2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongList extends Fragment {

    RecyclerView bookListRecycleView;
    SongListAdapter songListAdapter;
    List<Song> mSongList;


    private SongDAO mSongDAO;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.song_list, container, false);



        mSongDAO = new SongDAO(getContext());
        mSongList = mSongDAO.getAllBooks();


        bookListRecycleView = view.findViewById(R.id.book_list);
        bookListRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        songListAdapter = new SongListAdapter(getContext(), mSongList);
        bookListRecycleView.setAdapter(songListAdapter);

        songListAdapter.setOnItemClickListener(new SongListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song song) {
                Intent intent = new Intent(getActivity(), SongDetailActivity.class);
                Log.d("Tuan", "onItemClick: " + song.toString());
                intent.putExtra("book", song);
                startActivity(intent);
            }
        });



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSongDAO.open();
        mSongList = mSongDAO.getAllBooks();
        mSongDAO.close();
        songListAdapter.setBookList(mSongList);
    }
}
