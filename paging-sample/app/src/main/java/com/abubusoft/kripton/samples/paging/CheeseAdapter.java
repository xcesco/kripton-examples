package com.abubusoft.kripton.samples.paging;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CheeseAdapter extends RecyclerView.Adapter<CheeseAdapter.CheeseViewHolder> {

    List<Cheese> items;

    public class CheeseViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public Cheese item;

        public CheeseViewHolder(View view) {
            super(view);
            nameView = itemView.findViewById(R.id.name);
        }
    }

    public CheeseAdapter() {
        this.items = new ArrayList<>();
    }

    public void update(List<Cheese> items) {
        this.items.clear();
        this.items.addAll(items);
        this.notifyDataSetChanged();
    }

    @Override
    public CheeseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cheese_item, parent, false);

        return new CheeseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CheeseViewHolder holder, int position) {
        holder.item = items.get(position);
        holder.nameView.setText(holder.item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
