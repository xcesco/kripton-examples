package com.abubusoft.kripton.samples.paging;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRecyclerViewAdapter<T, VH extends AbstractRecyclerViewAdapter.ViewHolder<T>> extends RecyclerView.Adapter<VH> {

    public AbstractRecyclerViewAdapter() {
        dataset = new ArrayList<>();
    }

    public ArrayList<T> dataset;

    public void removeAt(int position) {
        dataset.remove(position);
    }

    public T get(int position) {
        return dataset.get(position);
    }

    public boolean isEmpty() {
        return dataset.isEmpty();
    }

    public void clear() {
        dataset.clear();
    }

//    public static class Utility {
//
//        public static <E extends ViewHolder> View getItemView(E bean) {
//            return null;
//        }
//    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static abstract class ViewHolder<T> extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);

        }

        public abstract void bindToView(View view);

        public abstract void bindToItem(T item);
    }

    public void add(int position, T item) {
        dataset.add(position, item);
        notifyItemInserted(position);
    }

    public void update(List<T> items) {
       // if (items.size() != dataset.size()) {
            dataset.clear();

            dataset.addAll(items);
            notifyDataSetChanged();
       // }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public AbstractRecyclerViewAdapter(ArrayList<T> myDataset) {
        dataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VH onCreateViewHolder(ViewGroup parent,
                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cheese_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        VH vh = createViewHolder(v);
        vh.bindToView(v);

        return vh;
    }

    protected abstract VH createViewHolder(View v);

    public void onBindItem(VH holder, T item) {
        holder.bindToItem(item);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(VH holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final T item = dataset.get(position);


        onBindItem(holder, item);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }

}  