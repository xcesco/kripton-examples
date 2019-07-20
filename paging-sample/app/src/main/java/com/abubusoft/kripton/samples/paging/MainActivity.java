package com.abubusoft.kripton.samples.paging;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView inputText;
    private Button addButton;
    private CheeseViewModel viewModel;
    private RecyclerView cheeseList;

    private TextView tvCurrentPage;
    private Button previousButton;
    private Button nextButton;
    private ProgressBar progressBar;
    private TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inputText=findViewById(R.id.inputText);
        this.addButton=findViewById(R.id.addButton);
        this.cheeseList=findViewById(R.id.cheeseList);
        this.progressBar=findViewById(R.id.progressBar);

        this.tvCurrentPage=findViewById(R.id.textViewCurrentPage);
        this.tvCount=findViewById(R.id.textViewCount);

        this.previousButton=findViewById(R.id.previousButton);
        this.nextButton=findViewById(R.id.nextButton);

        viewModel=ViewModelProviders.of(this).get(CheeseViewModel.class);

        CheeseAdapter adapter = new CheeseAdapter();
        cheeseList.setAdapter(adapter);

        this.viewModel.getAllCheeses().observe(this, cheeses -> {
            if (viewModel.isFirstPage()) {
                previousButton.setEnabled(false);
            } else {
                previousButton.setEnabled(true);
            }
            adapter.update(cheeses);
            if (cheeses.size()==0) {
                nextButton.setEnabled(false);
            } else if (!nextButton.isEnabled()) {
                nextButton.setEnabled(true);
            }
            tvCurrentPage.setText(this.viewModel.getCurrentPageIndex()+" / ");
            this.progressBar.setVisibility(View.INVISIBLE);
        });

        this.viewModel.getCheeseCount().observe(this, value -> tvCount.setText(""+value));


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
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.previousPage();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.nextPage();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    private void initSwipeToDelete() {

        new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return  makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.remove(((CheeseAdapter.CheeseViewHolder)viewHolder).item);
                progressBar.setVisibility(View.VISIBLE);
            }
        }).attachToRecyclerView(cheeseList);
    }

    private void addCheese() {
        String newCheese = inputText.getText().toString().trim();
        if (newCheese.length()>0) {
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
