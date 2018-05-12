package com.abubusoft.kripton.examples.rssreader.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.examples.rssreader.R;
import com.abubusoft.kripton.examples.rssreader.service.model.Article;
import com.abubusoft.kripton.examples.rssreader.service.repository.RssRepository;
import com.abubusoft.kripton.examples.rssreader.viewmodel.RssViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private final RssViewModel viewModel;
    private Context mContext;
    private List<Article> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, checked;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            checked = (ImageView) view.findViewById(R.id.checked);
        }
    }

    public ArticlesAdapter(Context mContext, RssViewModel viewModel, List<Article> list) {
        this.viewModel=viewModel;
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Article article = list.get(position);
        holder.title.setText(article.title);
        holder.count.setText(article.description);

        if (article.read) {
            holder.checked.setVisibility(View.VISIBLE);
        } else {
            holder.checked.setVisibility(View.INVISIBLE);
        }

        // loading album cover using Glide library
        Glide.with(mContext).load(article.thumbnail.url).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(article.link.toString()));
            KriptonLibrary.getContext().startActivity(intent);

            viewModel.markArticleAsRead(article);
        });

        holder.checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.checked);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}