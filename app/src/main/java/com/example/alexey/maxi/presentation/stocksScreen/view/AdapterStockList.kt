package com.example.alexey.maxi.presentation.stocksScreen.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alexey.maxi.R
import com.example.alexey.maxi.domain.models.StockItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_resource.view.*


class AdapterStockList(val list: List<StockItem>) :
        RecyclerView.Adapter<AdapterStockList.StockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            StockViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.product_resource, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class StockViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bind(item: StockItem) {
            containerView.stock.text = item.title
            Picasso.get()
                    .load(item.image)
                    .fit()
                    .noFade()
                    .placeholder(R.drawable.maksi_placeholder)
                    .into(containerView.image_stock)
            containerView.price.text = "${item.priceNew} р."
            containerView.rubrics.text = item.rubricName?.joinToString()
        }
    }

}