package com.abubusoft.kripton.samples.paging;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CheeseAdapter extends AbstractRecyclerViewAdapter<Cheese, CheeseViewHolder> {

    @Override
    public CheeseViewHolder createViewHolder(View view) {
        return new CheeseViewHolder(view);
    }

}
