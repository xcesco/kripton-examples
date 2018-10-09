package com.abubusoft.kripton.movie.kriptonmovie.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.abubusoft.kripton.movie.kriptonmovie.R;
import com.abubusoft.kripton.movie.kriptonmovie.viewmodel.MoviesViewModel;

public class MovieSaveDialogFragment extends DialogFragment {
    private String movieTitleExtra;
    private String movieDirectorFullNameExtra;

    private static final String EXTRA_MOVIE_TITLE = "movie_title";
    private static final String EXTRA_MOVIE_DIRECTOR_FULL_NAME = "movie_director_full_name";
    public static final String TAG_DIALOG_MOVIE_SAVE = "dialog_movie_save";
    private MoviesViewModel moviesViewModel;

    public static MovieSaveDialogFragment newInstance(final String movieTitle, final String movieDirectorFullName) {
        MovieSaveDialogFragment fragment = new MovieSaveDialogFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_MOVIE_TITLE, movieTitle);
        args.putString(EXTRA_MOVIE_DIRECTOR_FULL_NAME, movieDirectorFullName);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        movieTitleExtra = args.getString(EXTRA_MOVIE_TITLE);
        movieDirectorFullNameExtra = args.getString(EXTRA_MOVIE_DIRECTOR_FULL_NAME);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_movie, null);
        final EditText movieEditText = view.findViewById(R.id.etMovieTitle);
        final EditText movieDirectorEditText = view.findViewById(R.id.etMovieDirectorFullName);
        if (movieTitleExtra != null) {
            movieEditText.setText(movieTitleExtra);
            movieEditText.setSelection(movieTitleExtra.length());
        }
        if (movieDirectorFullNameExtra != null) {
            movieDirectorEditText.setText(movieDirectorFullNameExtra);
            movieDirectorEditText.setSelection(movieDirectorFullNameExtra.length());
        }
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_movie_title))
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveMovie(movieEditText.getText().toString(), movieDirectorEditText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return alertDialogBuilder.create();
    }

    private void saveMovie(String movieTitle, String movieDirectorFullName) {
        if (TextUtils.isEmpty(movieTitle) || TextUtils.isEmpty(movieDirectorFullName)) {
            return;
        }

        moviesViewModel.insertOrUpdateMovie(movieTitle, movieTitleExtra, movieDirectorFullName);


    }
}