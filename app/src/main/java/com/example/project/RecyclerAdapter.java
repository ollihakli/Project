package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<Movie> movieList;
    private RecyclerViewClickListener listener;

    public RecyclerAdapter(ArrayList<Movie> movieList, RecyclerViewClickListener listener) {
        this.movieList = movieList;
        this.listener = listener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameText;

        public MyViewHolder(final View view) {
            super(view);
            nameText = view.findViewById(R.id.textView2);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent, false);
        return new MyViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String name = movieList.get(position).getMovieName();
        holder.nameText.setText(name);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
