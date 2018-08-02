package com.abubusoft.kripton.example.rssreader.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.abubusoft.kripton.android.KriptonLibrary
import com.abubusoft.kripton.example.rssreader.R
import com.abubusoft.kripton.example.rssreader.service.model.Article
import com.abubusoft.kripton.example.rssreader.viewmodel.RssViewModel
import com.bumptech.glide.Glide

class ArticlesAdapter(private val mContext: Context, private val viewModel: RssViewModel, private val list: MutableList<Article>) : RecyclerView.Adapter<ArticlesAdapter.MyViewHolder>() {

    fun updateValues(articles: List<Article>) {
        list.clear()
        list.addAll(articles)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var count: TextView
        var thumbnail: ImageView
        var checked: ImageView

        init {
            title = view.findViewById(R.id.title) as TextView
            count = view.findViewById(R.id.count) as TextView
            thumbnail = view.findViewById(R.id.thumbnail) as ImageView
            checked = view.findViewById(R.id.checked) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.article_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = list[position]
        holder.title.setText(article.title)
        holder.count.setText(article.description)

        if (article.read) {
            holder.checked.visibility = View.VISIBLE
        } else {
            holder.checked.visibility = View.INVISIBLE
        }

        // loading album cover using Glide library
        Glide.with(mContext).load(article.thumbnail?.url).into(holder.thumbnail)

        holder.thumbnail.setOnClickListener { _ ->
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(article.link.toString()))
            KriptonLibrary.getContext().startActivity(intent)

            viewModel.markArticleAsRead(article)
        }

        holder.checked.setOnClickListener { showPopupMenu(holder.checked) }
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private fun showPopupMenu(view: View) {
        // inflate menu
        val popup = PopupMenu(mContext, view)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.menu_main, popup.menu)
        popup.setOnMenuItemClickListener(MyMenuItemClickListener())
        popup.show()
    }

    /**
     * Click listener for popup menu items
     */
    internal inner class MyMenuItemClickListener : PopupMenu.OnMenuItemClickListener {

        override fun onMenuItemClick(menuItem: MenuItem): Boolean {/*

            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }*/
            return false
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}