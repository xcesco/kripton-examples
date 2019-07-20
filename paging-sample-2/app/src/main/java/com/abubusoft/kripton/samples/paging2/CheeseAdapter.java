package com.abubusoft.kripton.samples.paging2;

import androidx.lifecycle.LifecycleOwner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abubusoft.kripton.androidx.livedata.PagedLiveData;
import com.abubusoft.kripton.androidx.widgets.CustomDiffCallback;

import java.util.List;

public class CheeseAdapter extends AbstractRecyclerViewAdapter<CheeseAdapter.CheeseViewHolder> {

    public CheeseAdapter(LifecycleOwner context, PagedLiveData<List<Cheese>> pagedResult, CustomDiffCallback<Cheese> diff, OnLoadingListener loadingListener) {
        super(context, pagedResult, diff, loadingListener);
    }

    public class CheeseViewHolder extends AbstractRecyclerViewAdapter.ViewHolder {
        public TextView nameView;
        public Cheese item;

        public CheeseViewHolder(ViewGroup parent,
                                int viewType) {
            super(parent, viewType);

            nameView = itemView.findViewById(R.id.name);
        }

        @Override
        public void bindToView(View parentView) {

        }

    }

    @Override
    public CheeseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cheese_item, parent, false);

        return new CheeseViewHolder(parent, viewType);
    }

    @Override
    public int getViewLayoutResourceId() {
        return R.layout.cheese_item;
    }

    @Override
    public void onBindItem(CheeseViewHolder holder, Cheese item) {
        holder.item = item;

        if (item!=null) {
            holder.nameView.setText(holder.item.getName());
        }
    }

}
