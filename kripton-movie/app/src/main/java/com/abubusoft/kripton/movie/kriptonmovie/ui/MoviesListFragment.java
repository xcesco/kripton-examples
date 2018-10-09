package com.abubusoft.kripton.movie.kriptonmovie.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.movie.kriptonmovie.R;
import com.abubusoft.kripton.movie.kriptonmovie.model.MovieWithDirector;
import com.abubusoft.kripton.movie.kriptonmovie.viewmodel.MoviesViewModel;

import java.util.List;

public class MoviesListFragment extends Fragment {
    private MoviesListAdapter moviesListAdapter;
    private MoviesViewModel moviesViewModel;
    private Context context;

    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        moviesListAdapter = new MoviesListAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_movies);
        recyclerView.setAdapter(moviesListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    private void initData() {
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getMoviesList().observe(this, (List<MovieWithDirector> movies) -> {
            Logger.info("using live data to populate movies");
            moviesListAdapter.setMovieList(movies);
        });
    }

    public void removeData() {
        if (moviesListAdapter != null) {
            moviesViewModel.deleteAll();
        }
    }
}