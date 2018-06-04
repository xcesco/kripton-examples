package com.abubusoft.sharedpreferencewatcher.view.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.abubusoft.kripton.android.annotation.BindPreferenceAdapter;
import com.abubusoft.sharedpreferencewatcher.model.BindAppPreferences;
import com.abubusoft.sharedpreferencewatcher.view.adapters.RecyclerViewAdapter;

import com.abubusoft.sharedpreferencewatcher.AbstractModel;
import com.abubusoft.sharedpreferencewatcher.R;
import com.abubusoft.sharedpreferencewatcher.viewmodel.SharedPrefsViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Toolbar toolbar;

    private RecyclerViewAdapter mAdapter;

    private ArrayList<AbstractModel> modelList = new ArrayList<>();
    private SharedPrefsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initToolbar("Kripton and Shared Prefs Live Data!");
        setAdapter();

        // view model management
        // 1- create view model
        viewModel = ViewModelProviders.of(this).get(SharedPrefsViewModel.class);
        // 2- observe channel header
        viewModel.getExampleList().observe(this, value ->
                this.mAdapter.update(new AbstractModel("Key = ExampleList", "Value = " + value))
        );
        viewModel.getExampleSwitch().observe(this, value ->
                this.mAdapter.update(new AbstractModel("Key = ExampleSwitch", "Value = " + value))
        );
        viewModel.getExampleText().observe(this, value ->
                this.mAdapter.update(new AbstractModel("Key = ExampleText", "Value = " + value))
        );
    }

    private void setAdapter() {
        mAdapter = new RecyclerViewAdapter(MainActivity.this, modelList);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    public void initToolbar(String title) {
        setSupportActionBar(toolbar);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        /*getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
