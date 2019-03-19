package com.hogp.testingeverything.recyclerviewfilters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hogp.testingeverything.R
import com.hogp.testingeverything.recyclerviewfilters.edmtv.model.Item

class MyAdapter(internal var context: Context,
                internal var itemList: List<Item>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(), Filterable {

    internal var filterListResult: List<Item>

    init {
        this.filterListResult = itemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return filterListResult.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_title.text = filterListResult.get(position).title
        holder.txt_url.text = filterListResult.get(position).url


    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch: String = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterListResult = itemList
                } else {
                    val resultList = ArrayList<Item>()
                    for (row in itemList) {
                        if (row.title!!.toLowerCase().contains(charSearch.toLowerCase()))
                            resultList.add(row)
                    }

                }
                val filterResults = Filter.FilterResults()
                filterResults.values = filterListResult
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterListResult = results!!.values as List<Item>
                notifyDataSetChanged(

                )
            }

        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txt_title: TextView
        internal var txt_url: TextView

        init {
            txt_title = itemView.findViewById<TextView>(R.id.title)
            txt_url = itemView.findViewById<TextView>(R.id.url)
        }
    }
}