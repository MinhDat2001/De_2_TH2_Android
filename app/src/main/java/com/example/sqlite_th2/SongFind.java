package com.example.sqlite_th2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class SongFind extends Fragment {

    RecyclerView bookListRecycleView;
    SongListAdapter songListAdapter;
    List<Song> mSongList;
    Double startPrice, endPrice;
    String publisher;
    EditText startEdt, endEdt;

    Spinner mfindSpinner;
    Button findBtn;
    TextView records;
    private SongDAO mSongDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.song_find, container, false);

        mSongDAO = new SongDAO(getContext());
        mSongList = mSongDAO.getAllBooks();

        bookListRecycleView = view.findViewById(R.id.book_find_list);
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


        startEdt = view.findViewById(R.id.from);
        endEdt = view.findViewById(R.id.to);
        findBtn = view.findViewById(R.id.findBtn);
        records = view.findViewById(R.id.sizeRecord);
        mfindSpinner = view.findViewById(R.id.find_spn_publisher);

        List<String> publishers = Arrays.asList(getResources().getStringArray(R.array.find_type_array));
        PublisherAdapter adapter = new PublisherAdapter(getContext(), android.R.layout.simple_spinner_item, publishers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mfindSpinner.setAdapter(adapter);

//        findBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // chọn tất cả sách ở tất cả khoảng giá của tất cả nhà xuất bản
//                if (TextUtils.isEmpty(startEdt.getText().toString().trim()) && (TextUtils.isEmpty(endEdt.getText().toString().trim())) && mfindSpinner.getSelectedItem().toString().equals("Chọn tất cả")) {
//                    mSongList = mSongDAO.getAllBooks();
//                    records.setText("Tất cả bản ghi:");
//                    songListAdapter.setBookList(mSongList);
//                } else if (TextUtils.isEmpty(startEdt.getText().toString().trim()) && (TextUtils.isEmpty(endEdt.getText().toString().trim())) && !mfindSpinner.getSelectedItem().toString().equals("Chọn tất cả")) {
//                    publisher = mfindSpinner.getSelectedItem().toString();
//                    mSongList = mSongDAO.searchByPublisher(publisher);
//                    records.setText("Tìm thấy " + mSongList.size() + " bản ghi:");
//                    Song.sortBooksByPriceDescending(mSongList);
//                    songListAdapter.setBookList(mSongList);
//                } else if (TextUtils.isEmpty(startEdt.getText().toString().trim()) || (TextUtils.isEmpty(endEdt.getText().toString().trim())))
//                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
//                else {
//                    publisher = mfindSpinner.getSelectedItem().toString();
//                    startPrice = Double.parseDouble(startEdt.getText().toString().trim());
//                    endPrice = Double.parseDouble(endEdt.getText().toString().trim());
//                    if (startPrice < 0 || endPrice < 0) {
//                        Toast.makeText(getContext(), "Nhập số tiền dương", Toast.LENGTH_SHORT).show();
//                    } else if (startPrice > endPrice) {
//                        Toast.makeText(getContext(), "Giá sau phải cao hơn giá trước", Toast.LENGTH_SHORT).show();
//                    } else if (mfindSpinner.getSelectedItem().toString().equals("Chọn tất cả")) {
//                        mSongList = mSongDAO.searchByPrice(startPrice, endPrice);
//                        records.setText("Tìm thấy " + mSongList.size() + " bản ghi:");
//                        Song.sortBooksByPriceDescending(mSongList);
//                        songListAdapter.setBookList(mSongList);
//                    } else {
//                        mSongList = mSongDAO.searchByPriceAndPublisher(startPrice, endPrice, publisher);
//                        records.setText("Tìm thấy " + mSongList.size() + " bản ghi:");
//                        Song.sortBooksByPriceDescending(mSongList);
//                        songListAdapter.setBookList(mSongList);
//                    }
//                }
//            }
//        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSongList = mSongDAO.getAllBooks();
        songListAdapter.setBookList(mSongList);
    }
}
