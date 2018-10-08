package com.abubusoft.kripton.samples.paging;

import android.view.View;
import android.widget.TextView;

public class CheeseViewHolder extends AbstractRecyclerViewAdapter.ViewHolder<Cheese> {
    public CheeseViewHolder(View itemView) {
        super(itemView);

    }

    @Override
    public void bindToView(View view) {
        nameView = itemView.findViewById(R.id.name);
    }

    @Override
    public void bindToItem(Cheese item) {
        this.cheese = item;
        nameView.setText(cheese.getName());

    }

    private TextView nameView;

    public Cheese cheese;

}
