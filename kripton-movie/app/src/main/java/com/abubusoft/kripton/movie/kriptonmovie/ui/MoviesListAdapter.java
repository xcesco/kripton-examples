package com.abubusoft.kripton.movie.kriptonmovie.ui;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abubusoft.kripton.movie.kriptonmovie.R;
import com.abubusoft.kripton.movie.kriptonmovie.model.Director;
import com.abubusoft.kripton.movie.kriptonmovie.model.Movie;
import com.abubusoft.kripton.movie.kriptonmovie.model.MovieWithDirector;

import java.util.List;

import static com.abubusoft.kripton.movie.kriptonmovie.ui.MovieSaveDialogFragment.TAG_DIALOG_MOVIE_SAVE;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder> {
    private LayoutInflater layoutInflater;
    private List<MovieWithDirector> movieList;
    private Context context;

    public MoviesListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setMovieList(List<MovieWithDirector> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_movie, parent, false);
        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        if (movieList == null) {
            return;
        }

        final MovieWithDirector movie = movieList.get(position);
        if (movie != null) {
            holder.titleText.setText(movie.title);

            String directorFullName;
            if (movie.fullName != null) {
                holder.directorText.setText(movie.fullName);
                directorFullName = movie.fullName;
            } else {
                directorFullName = "";
            }
            holder.itemView.setOnClickListener((View v) -> {
                DialogFragment dialogFragment = MovieSaveDialogFragment.newInstance(movie.title, directorFullName);
                dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), TAG_DIALOG_MOVIE_SAVE);
            });
        }
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        } else {
            return movieList.size();
        }
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private TextView directorText;

        public MoviesViewHolder(View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.tvMovieTitle);
            directorText = itemView.findViewById(R.id.tvMovieDirectorFullName);
        }
    }
}