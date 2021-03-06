package com.abubusoft.kripton.movie.kriptonmovie.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.abubusoft.kripton.movie.kriptonmovie.R;
import com.abubusoft.kripton.movie.kriptonmovie.persistence.MovieRepository;
import com.abubusoft.kripton.movie.kriptonmovie.viewmodel.DirectorsViewModel;
import com.abubusoft.kripton.movie.kriptonmovie.viewmodel.MoviesViewModel;

import static com.abubusoft.kripton.movie.kriptonmovie.ui.DirectorSaveDialogFragment.TAG_DIALOG_DIRECTOR_SAVE;
import static com.abubusoft.kripton.movie.kriptonmovie.ui.MovieSaveDialogFragment.TAG_DIALOG_MOVIE_SAVE;

public class MainActivity extends AppCompatActivity {

    private boolean MOVIES_SHOWN = true;
    private Fragment shownFragment;
    private MoviesViewModel moviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(getString(R.string.app_name));
        initView();

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);


        if (savedInstanceState == null) {
            showFragment(MoviesListFragment.newInstance());
        }
    }
    public void setToolbar(@NonNull String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

    private void initView() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    MOVIES_SHOWN = true;
                    showFragment(MoviesListFragment.newInstance());
                    return true;
                case R.id.navigation_directors:
                    MOVIES_SHOWN = false;
                    showFragment(DirectorsListFragment.newInstance());
                    return true;
            }
            return false;
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> showSaveDialog());
    }

    private void showFragment(final Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolder, fragment);
        fragmentTransaction.commitNow();
        shownFragment = fragment;
    }

    private void showSaveDialog() {
        DialogFragment dialogFragment;
        String tag;
        if (MOVIES_SHOWN) {
            dialogFragment = MovieSaveDialogFragment.newInstance(null, null);
            tag = TAG_DIALOG_MOVIE_SAVE;
        } else {
            dialogFragment = DirectorSaveDialogFragment.newInstance(null);
            tag = TAG_DIALOG_DIRECTOR_SAVE;
        }

        dialogFragment.show(getSupportFragmentManager(), tag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.action_delete_list_data) {
            deleteCurrentListData();
            return true;
        } else if (id == R.id.action_re_create_database) {
            reCreateDatabase();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteCurrentListData() {
        if (MOVIES_SHOWN) {
            ((MoviesListFragment) shownFragment).removeData();
        } else {
            ((DirectorsListFragment) shownFragment).removeData();
        }
    }

    private void reCreateDatabase() {
        moviesViewModel.clearDb();
    }
}
