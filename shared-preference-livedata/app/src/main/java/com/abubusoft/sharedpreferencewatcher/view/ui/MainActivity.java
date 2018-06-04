package com.abubusoft.sharedpreferencewatcher.view.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.abubusoft.sharedpreferencewatcher.R;
import com.abubusoft.sharedpreferencewatcher.viewmodel.SharedPrefsViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private SharedPrefsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView=(TextView)findViewById(R.id.textView);

        // view model management
        // 1- create view model
        viewModel = ViewModelProviders.of(this).get(SharedPrefsViewModel.class);
        // 2- observe channel header
        viewModel.getExampleList().observe(this, value -> textView.append(String.format("\nExample List ='%s'",value)));
        viewModel.getExampleSwitch ().observe(this, value -> textView.append(String.format("\nExample Switch ='%s'",value)));
        viewModel.getExampleText().observe(this, value -> textView.append(String.format("\nExample Text ='%s'",value)));
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
