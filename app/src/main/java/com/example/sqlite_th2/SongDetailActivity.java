package com.example.sqlite_th2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class SongDetailActivity extends AppCompatActivity {
    int id;
    String name, singer, album, type;
    boolean favorite;
    private Spinner mAlbumSpinner;
    private Spinner mTypeSpinner;
    private TextView mId;
    private EditText mNameEditText;
    private EditText mSingerEditText;
    private CheckBox mFavorite;
    private Button buttonChange;
    private Button buttonDelete;
    private Button buttonBack;
    private SongDAO mSongDAO;

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItem(position).equals(value)) {
                spnr.setSelection(position);
                return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        Intent intent = getIntent();
        Song song = (Song) intent.getSerializableExtra("book");
        id = song.getId();
        name = song.getName();
        singer = song.getSinger();
        album = song.getAlbum();
        type = song.getType();
        favorite = song.isFavorite();


        mAlbumSpinner = findViewById(R.id.spn_publisher);
        mTypeSpinner = findViewById(R.id.spn_type);
        mId = findViewById(R.id.editId);
        mNameEditText = findViewById(R.id.editTextName);
        mSingerEditText = findViewById(R.id.editTextAuthor);
        mTypeSpinner = findViewById(R.id.spn_type);
        mFavorite = findViewById(R.id.cb_favorite);
        buttonChange = findViewById(R.id.buttonChange);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonBack = findViewById(R.id.buttonBack);
        mSongDAO = new SongDAO(this);

        mId.setText(Integer.toString(id));
        mNameEditText.setText(name);
        mSingerEditText.setText(singer);
        selectSpinnerItemByValue(mAlbumSpinner, album);
        selectSpinnerItemByValue(mTypeSpinner, type);
        mFavorite.setChecked(favorite);

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idd = Integer.parseInt(mId.getText().toString());
                String named = mNameEditText.getText().toString();
                String singer = mSingerEditText.getText().toString();
                String album = mAlbumSpinner.getSelectedItem().toString();
                String type = mTypeSpinner.getSelectedItem().toString();
                boolean favorite = mFavorite.isChecked();
                Song song = new Song(idd, named, singer, album, type, favorite);
                mSongDAO.updateBook(song);
                Toast.makeText(SongDetailActivity.this, "Bản ghi số " + idd + " đã được cập nhật", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSongDAO.deleteBook(song);
                Toast.makeText(SongDetailActivity.this, "Bản ghi số " + song.getId()+ " đã bị xoá", Toast.LENGTH_SHORT).show();
                onBackPressed();
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
        mAlbumSpinner.setAdapter(adapter);
    }
}