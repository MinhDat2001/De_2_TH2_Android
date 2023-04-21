package com.example.sqlite_th2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {
    private List<Song> mSongList;

    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Song song);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView;
        public TextView nameTextView;
        public TextView authorTextView;
        public TextView releaseDateTextView;
        public TextView publisherTextView;
        public TextView priceTextView;

        public ViewHolder(View v) {
            super(v);
            idTextView = v.findViewById(R.id.idTextView);
            nameTextView = v.findViewById(R.id.nameTextView);
            authorTextView = v.findViewById(R.id.authorTextView);
            releaseDateTextView = v.findViewById(R.id.releaseDateTextView);
            publisherTextView = v.findViewById(R.id.publisherTextView);
            priceTextView = v.findViewById(R.id.priceTextView);
        }

        public void bind(final Song song, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(song);
                }
            });
        }
    }

    public SongListAdapter(Context context, List<Song> songList) {
        this.context = context;
        mSongList = songList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = mSongList.get(position);
        holder.idTextView.setText(Integer.toString(song.getId()));
        holder.nameTextView.setText(song.getName());
        holder.authorTextView.setText("Ca sĩ: " + song.getSinger());
        holder.releaseDateTextView.setText(song.getAlbum());
        holder.publisherTextView.setText(song.getType());
        holder.priceTextView.setText(String.valueOf(song.isFavorite()));
        holder.bind(song, listener);
    }

    @Override
    public int getItemCount() {
        return mSongList.size();
    }

    public void setBookList(List<Song> songList) {
        mSongList = songList;
        notifyDataSetChanged();
    }
}
