package com.abubusoft.filmfinder.view.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.abubusoft.filmfinder.R;
import com.abubusoft.filmfinder.viewmodel.FilmViewModel;
import com.abubusoft.kripton.android.Logger;

public class MainActivity extends AppCompatActivity {

    private FilmViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(view-> {
            viewModel.setSearchFilter("star");
        });

        // view model management
        // 1- create view model
        viewModel = ViewModelProviders.of(this).get(FilmViewModel.class);

        viewModel.setSearchFilter("star");
        viewModel.getFilms().observe(this, result -> {
            result.getSearch();
            Logger.info("Finito!!! "+result.getTotalResults());
        });



    }
}
