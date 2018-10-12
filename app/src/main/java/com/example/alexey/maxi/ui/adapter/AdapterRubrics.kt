package com.example.alexey.maxi.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alexey.maxi.R
import com.example.alexey.maxi.data.network.RubrickItem

class AdapterRubrics(val click: OnClick, val list: List<RubrickItem>) :
        RecyclerView.Adapter<AdapterRubrics.RubricViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            AdapterRubrics.RubricViewHolder(click, LayoutInflater.from(parent.context)
                    .inflate(R.layout.rubrics_item, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: AdapterRubrics.RubricViewHolder, position: Int) {
        val rubrickItem = list[position]
        holder.bindTitle(rubrickItem.name)
    }

    class RubricViewHolder(val click: OnClick, val containerView: View) : RecyclerView.ViewHolder(containerView),
            View.OnClickListener {

        override fun onClick(v: View?) {
            click.onItemClick(layoutPosition)
        }

        fun bindTitle(title: String) {
            containerView.rubric_title.text = title
        }
    }
}

interface OnClick {
    fun onItemClick(position: Int)
}