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
import com.abubusoft.kripton.movie.kriptonmovie.model.Director;
import com.abubusoft.kripton.movie.kriptonmovie.viewmodel.DirectorsViewModel;

import java.util.List;

public class DirectorsListFragment extends Fragment {
    private DirectorsListAdapter directorsListAdapter;
    private DirectorsViewModel directorsViewModel;
    private Context context;

    public static DirectorsListFragment newInstance() {
        return new DirectorsListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        directorsListAdapter = new DirectorsListAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_directors, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_directors);
        recyclerView.setAdapter(directorsListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void initData() {
        directorsViewModel = ViewModelProviders.of(this).get(DirectorsViewModel.class);
        directorsViewModel.getDirectorList().observe(this, directors -> {
            Logger.info("using live data to populate directors");
            directorsListAdapter.setDirectorList(directors);
        } );
    }

    public void removeData() {
        if (directorsViewModel != null) {
            directorsViewModel.deleteAll();
        }
    }
}