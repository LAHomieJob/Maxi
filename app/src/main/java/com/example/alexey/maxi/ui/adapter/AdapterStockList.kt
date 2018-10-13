package com.example.alexey.maxi.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alexey.maxi.R
import com.example.alexey.maxi.domain.models.StockItem
import com.squareup.picasso.Picasso


class AdapterStockList(val list: List<StockItem>) :
        RecyclerView.Adapter<AdapterStockList.StockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            StockViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.product_resource, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stockItem = list[position]
        holder.apply {
            bindTitle(stockItem.title)
            loadImage(stockItem.image)
            bindPrice(stockItem.priceNew)
        }
    }

    class StockViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bindTitle(title: String){
            containerView.stock.text = title
        }

        fun loadImage(url: String){
            Picasso.get()
                    .load(url)
                    .fit()
                    .placeholder(R.drawable.maksi_placeholder)
                    .into(containerView.image_stock)
        }

        fun bindPrice(price: Double){
            containerView.price.text = price.toString()
        }
    }

}