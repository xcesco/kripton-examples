package com.abubusoft.kripton.samples.paging;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CheeseViewHolder extends AbstractRecyclerViewAdapter.ViewHolder {
    public CheeseViewHolder(View itemView) {
        super(itemView);

        nameView = itemView.findViewById(R.id.name);
    }

    private TextView nameView;

    public Cheese cheese;

    public void bindTo(Cheese cheese) {
        this.cheese = cheese;
        nameView.setText(cheese.getName());
    }
}
