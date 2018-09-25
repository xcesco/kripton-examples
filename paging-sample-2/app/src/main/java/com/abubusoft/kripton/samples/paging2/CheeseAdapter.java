package com.abubusoft.kripton.samples.paging2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class CheeseAdapter extends RecyclerView.Adapter<CheeseAdapter.CheeseViewHolder> {

    private boolean firstUpdate=true;

    private final PageRequest pageRequest;

    List<Cheese> items;

    public class CheeseViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public Cheese item;

        public CheeseViewHolder(View view) {
            super(view);
            nameView = itemView.findViewById(R.id.name);
        }
    }

    public CheeseAdapter(PageRequest pageRequest) {
        this.items = new ArrayList<>();
        this.pageRequest=pageRequest;
    }

    public void update(List<Cheese> items) {
        if (firstUpdate) {
            this.items.clear();
            firstUpdate=false;
            
            //this.pageRequest.set
        } else {

        }
        this.items.addAll(items);

        notifyDataSetChanged();
    }

    @Override
    public CheeseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cheese_item, parent, false);

        return new CheeseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CheeseViewHolder holder, int position) {
        if (position==items.size()-1) {
            Logger.info("********************* pos: %3d, row: %3d",position, pageRequest.getOffset());
            this.pageRequest.nextPage();
        }

        Logger.info(">> item: %3d --> pos: %3d with firstRow: %3d",position, position, pageRequest.getOffset());
        holder.item = items.get(position);
        holder.nameView.setText(holder.item.getName());
    }

    @Override
    public int getItemCount() {
       // Logger.info("{{ array size: %3d <--> official size: %3d with firstRow: %3d }}",items.size(), pageRequest.getFirstRow()+pageRequest.getPageSize(), pageRequest.getFirstRow());
        return items.size();
    }
}
