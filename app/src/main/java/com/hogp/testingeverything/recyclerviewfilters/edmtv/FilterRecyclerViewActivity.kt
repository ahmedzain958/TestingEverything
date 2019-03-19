package com.hogp.testingeverything.recyclerviewfilters.edmtv

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hogp.testingeverything.R
import com.hogp.testingeverything.recyclerviewfilters.MyAdapter
import com.hogp.testingeverything.recyclerviewfilters.edmtv.model.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_filter_recycler_view.*
import kotlinx.android.synthetic.main.content_main.*

class FilterRecyclerViewActivity : AppCompatActivity() {


    lateinit var myApi: IMyAPI
    lateinit var adapter: MyAdapter
    var compositeDisposable = CompositeDisposable()
    var dataList: MutableList<Item> = ArrayList()
    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_recycler_view)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Filters"


        val retrofit = RetrofitCLient.getInstance
        myApi = retrofit.create(IMyAPI::class.java!!)


        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)


        adapter = MyAdapter(this, dataList)
        recycler_view.adapter = adapter
        fetchaData()
    }

    private fun fetchaData() {
        compositeDisposable.add(myApi.photos
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { photos ->
                    displayData(photos)
                }

        )

    }

    private fun displayData(photo: List<Item>?) {
        dataList.clear()
        dataList.addAll(photo!!)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchhManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu!!.findItem(R.id.action_search).actionView as SearchView
        searchView!!.setSearchableInfo(searchhManager.getSearchableInfo(componentName))
        searchView!!.maxWidth = Int.MAX_VALUE

        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId

        return if (id == R.id.action_search) true else super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (!searchView!!.isIconified) {
            searchView!!.isIconified = true
            return
        }
        super.onBackPressed()
    }

    override fun onStop() {
        compositeDisposable.dispose()
        super.onStop()
    }
}
