package com.abubusoft.kripton.samples.paging;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView inputText;
    private Button addButton;
    private CheeseViewModel viewModel;
    private RecyclerView cheeseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inputText=findViewById(R.id.inputText);
        this.addButton=findViewById(R.id.addButton);
        this.cheeseList=findViewById(R.id.cheeseList);

        this.viewModel=ViewModelProviders.of(this).get(CheeseViewModel.class);

        CheeseAdapter adapter = new CheeseAdapter();
        cheeseList.setAdapter(adapter);


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
                viewModel.remove(((CheeseViewHolder)viewHolder).cheese);
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
            }
        });

        // when the user taps the "Done" button in the on screen keyboard, save the item.
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addCheese();
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
