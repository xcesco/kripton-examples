package com.abubusoft.kripton.samples.paging2;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.samples.paging2.com.abubusoft.kripton.widgetx.KriptonRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView inputText;
    private Button addButton;
    private CheeseViewModel viewModel;
    private RecyclerView cheeseList;

    private TextView tvCurrentPage;
    private Button nextButton;
    private ProgressBar progressBar;
    private TextView tvCount;
    private Button refreshButton;
    private CheeseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inputText = findViewById(R.id.inputText);
        this.addButton = findViewById(R.id.addButton);
        this.cheeseList = findViewById(R.id.cheeseList);
        this.progressBar = findViewById(R.id.progressBar);

        this.tvCurrentPage = findViewById(R.id.textViewCurrentPage);
        this.tvCount = findViewById(R.id.textViewCount);

        this.refreshButton = findViewById(R.id.refreshButton);
        this.nextButton = findViewById(R.id.nextButton);

        viewModel = ViewModelProviders.of(this).get(CheeseViewModel.class);

        adapter = new CheeseAdapter(this, viewModel.getAllCheeses(), null, (loading, position, lenght, total) -> {

            if (loading) {
                this.progressBar.setVisibility(View.VISIBLE);
            } else {
                //tvCurrentPage.setText(total);
                this.progressBar.setVisibility(View.INVISIBLE);
            }
        });

        cheeseList.setAdapter(adapter);

//        this.viewModel.getAllCheeses().observe(this, cheeses -> {
//            adapter.update(cheeses);
//            tvCurrentPage.setText(this.viewModel.getCurrentPageIndex()+" / ");
//            this.progressBar.setVisibility(View.INVISIBLE);
//        });

        initButtons();
        initSwipeToDelete();
        initAddButtonListener();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void initButtons() {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.info("=== REFRESH ===");
                MainActivity.this.adapter.notifyDataSetChanged();
            }
        });


    }

    private void initSwipeToDelete() {

        new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.remove(((CheeseAdapter.CheeseViewHolder) viewHolder).item);
                progressBar.setVisibility(View.VISIBLE);
            }
        }).attachToRecyclerView(cheeseList);
    }

    private void addCheese() {
        String newCheese = inputText.getText().toString().trim();
        if (newCheese.length() > 0) {
            viewModel.insert(newCheese);
            inputText.setText("");
        }
    }

    private void initAddButtonListener() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCheese();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        // when the user taps the "Done" button in the on screen keyboard, save the item.
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addCheese();
                    progressBar.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });

        // When the user clicks on the button, or presses enter, save the item.
        inputText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    addCheese();
                    return true;
                }
                return false; // event that isn't DOWN or ENTER occurred - ignore
            }
        });
    }

}
