package com.abubusoft.kripton.example.rssreader.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast

import com.abubusoft.kripton.android.Logger
import com.abubusoft.kripton.example.rssreader.R
import com.abubusoft.kripton.example.rssreader.service.model.Article
import com.abubusoft.kripton.example.rssreader.service.persistence.FilterType
import com.abubusoft.kripton.example.rssreader.view.adapter.ArticlesAdapter
import com.abubusoft.kripton.example.rssreader.viewmodel.RssViewModel

import com.bumptech.glide.Glide

import java.util.ArrayList


// https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/
// https://www.androidhive.info/2016/01/android-working-with-recycler-view/
class MainActivity : AppCompatActivity() {

    internal var recyclerView: RecyclerView? = null

    private var adapter: ArticlesAdapter? = null

    lateinit var viewModel: RssViewModel

    private var filterValues: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        filterValues = resources.getStringArray(R.array.article_filter_value)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        initCollapsingToolbar()

        // view model management
        // 1- create view model
        viewModel = ViewModelProviders.of(this).get(RssViewModel::class.java)
        // 2- observe channel header
        viewModel.channel.observe(this, Observer { channel ->
            if (channel != null) {
                Glide.with(this@MainActivity).load(channel.image?.url).into(findViewById(R.id.backdrop) as ImageView)
            } else {
                Logger.info("No data found, download articles")
                viewModel.downloadArticles()
            }
        })
        // 3- observe articles
        viewModel.articles.observe(this, Observer { articles -> prepareArticles(articles!!) })
        // 4- update filter
        viewModel.updateFilter(FilterType.ALL)

        // init adapter (it need viewModel)
        recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        adapter = ArticlesAdapter(this, viewModel, ArrayList<Article>())
        val mLayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10), true))
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.spinner)
        val spinner = item.actionView as Spinner

        val adapter = ArrayAdapter.createFromResource(applicationContext,
                R.array.article_filter_text, R.layout.spinner_item)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val filter = FilterType.valueOf(filterValues!![position])
                viewModel.updateFilter(filter)

                Toast.makeText(this@MainActivity,
                        "Filter value " + filterValues!![position],
                        Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        when (id) {
            R.id.action_refresh -> {
                viewModel.downloadArticles()
            }
        }
        /*    if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item)
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private fun initCollapsingToolbar() {
        val collapsingToolbar = findViewById(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        collapsingToolbar.title = " "
        val appBarLayout = findViewById(R.id.appbar) as AppBarLayout
        appBarLayout.setExpanded(true)

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.title = getString(R.string.app_name)
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar.title = " "
                    isShow = false
                }
            }
        })
    }

    /**
     * Adding few albums for testing
     */
    private fun prepareArticles(articles: List<Article>) {
        adapter!!.updateValues(articles)

        //albumList.add(a);
        adapter!!.notifyDataSetChanged()
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }
}
