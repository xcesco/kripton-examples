package com.abubusoft.kripton.samples.paging2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abubusoft.kripton.androidx.livedata.PagedLiveData;
import com.abubusoft.kripton.androidx.widgets.CustomDiffCallback;
import com.abubusoft.kripton.samples.paging2.com.abubusoft.kripton.widgetx.KriptonRecyclerViewAdapter;
import com.abubusoft.kripton.samples.paging2.com.abubusoft.kripton.widgetx.KriptonViewHolder;

import java.util.List;

public abstract class AbstractRecyclerViewAdapter<VH extends AbstractRecyclerViewAdapter.ViewHolder > extends KriptonRecyclerViewAdapter<Cheese, VH> {


    public AbstractRecyclerViewAdapter(androidx.lifecycle.LifecycleOwner context, PagedLiveData<List<Cheese>> pagedResult, CustomDiffCallback<Cheese> diff, OnLoadingListener loadingListener) {
        super(context, pagedResult, diff, loadingListener);
    }

    public static class Utility {

        public static <E extends ViewHolder> View getItemView(E bean) {
            return null;
        }
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static abstract class ViewHolder extends KriptonViewHolder<Cheese> {

        public ViewHolder(ViewGroup parent,
                          int viewType) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.cheese_item, parent, false));

        }

        public abstract void bindToView(View parentView);


    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(getViewLayoutResourceId(), parent, false);
        // set the view's size, margins, paddings and layout parameters
        VH vh = createViewHolder(parent, viewType);

        return vh;
    }

    public abstract int getViewLayoutResourceId();

    public abstract void onBindItem(VH holder, Cheese item);


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(VH holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Cheese item = viewBuffer.get(position);

        onBindItem(holder, item);
    }

}  