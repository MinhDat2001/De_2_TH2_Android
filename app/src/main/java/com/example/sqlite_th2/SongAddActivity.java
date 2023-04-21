package com.example.sqlite_th2;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class SongAddActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mSingerEditText;
    private Spinner mAlbumEditSpinner;
    private Spinner mTypeEditSpinner;
    private boolean mFavoriteEditText;
    private Button buttonSave;
    private Button buttonBack;
    private SongDAO mSongDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_add);

        mNameEditText = findViewById(R.id.editTextName);
        mSingerEditText = findViewById(R.id.editTextAuthor);
        mAlbumEditSpinner = findViewById(R.id.spn_publisher);
        mTypeEditSpinner = findViewById(R.id.spn_type);
        mFavoriteEditText = ((CheckBox) findViewById(R.id.cb_favorite)).isChecked();
        buttonSave = findViewById(R.id.buttonSave);
        buttonBack = findViewById(R.id.buttonBack);
        mSongDAO = new SongDAO(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString().trim();
                String singer = mSingerEditText.getText().toString().trim();
                String album = (String) mAlbumEditSpinner.getSelectedItem();
                String type = (String) mTypeEditSpinner.getSelectedItem();
                boolean favorite = ((CheckBox) findViewById(R.id.cb_favorite)).isChecked();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(singer) || TextUtils.isEmpty(album) || TextUtils.isEmpty(type))
                    Toast.makeText(SongAddActivity.this, "Hãy điền đầy đủ các mục", Toast.LENGTH_SHORT).show();
                else {
                    Song song = new Song(name, singer, album, type, favorite);
                    Log.d("Tuan", "onClick: " + name + "\n" + singer + "\n" + album + "\n" + favorite);
                    mSongDAO.addBook(song);
                    Toast.makeText(SongAddActivity.this, "Thêm thành công sách " + name, Toast.LENGTH_SHORT).show();
                    mNameEditText.setText(null);
                    mSingerEditText.setText(null);
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        List<String> publishers = Arrays.asList(getResources().getStringArray(R.array.publishers_array));
        PublisherAdapter adapter = new PublisherAdapter(this, android.R.layout.simple_spinner_item, publishers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAlbumEditSpinner.setAdapter(adapter);
    }



}
