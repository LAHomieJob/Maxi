package com.example.alexey.maxi.presentation.rubricsScreen.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alexey.maxi.R
import com.example.alexey.maxi.domain.models.Rubric
import kotlinx.android.synthetic.main.rubrics_item.view.*

class AdapterRubrics(val clickListener: (Rubric) -> Unit, val list: List<Rubric>) :
        RecyclerView.Adapter<AdapterRubrics.RubricViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RubricViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.rubrics_item, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RubricViewHolder, position: Int) {
        val rubrickItem = list[position]
        holder.bind(item = rubrickItem, clickListener = clickListener)
    }

    class RubricViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bind(item: Rubric, clickListener: (Rubric) -> Unit) {
            containerView.rubric_title.text = item.name
            containerView.setOnClickListener { clickListener(item) }
        }
    }
}