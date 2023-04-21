package com.example.sqlite_th2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SongFavorite extends Fragment {

    private ImageView bookCoverImageView;
    private TextView bookTitleTextView;
    private TextView bookAuthorTextView;
    private TextView bookReviewTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.song_favorite, container, false);

        bookCoverImageView = view.findViewById(R.id.book_cover);
        bookTitleTextView = view.findViewById(R.id.book_title);
        bookReviewTextView = view.findViewById(R.id.book_review);

        bookTitleTextView.setText("Jisoo");
        bookReviewTextView.setText("\"Là thành viên Blackpink\" Kim Ji-soo (tiếng Hàn: 김지수; sinh ngày 3 tháng 1 năm 1995), thường được biết đến với nghệ danh Jisoo, là một nữ ca sĩ, diễn viên, người mẫu, người dẫn chương trình người Hàn Quốc, thành viên chị cả của nhóm nhạc nữ Blackpink do YG Entertainment thành lập và quản lý.\n" + "\n" + "\"Pink venom\" "+ "\n" + "\"Kill this love\" ");
        return view;
    }
}
