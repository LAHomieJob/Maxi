package com.example.alexey.maxi.ui.fragments.stock;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.alexey.maxi.ui.adapter.AdapterStockList;

public class StockFragment extends MvpAppCompatFragment {

    private static final String PARENT_RUBRIC = "Parent rubric";

    private RecyclerView list;
    private LinearLayoutManager layoutManager;
    private AdapterStockList adapter;

    public static StockFragment createInstance(int parentRubric) {
        StockFragment stockFragment = new StockFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARENT_RUBRIC, parentRubric);
        stockFragment.setArguments(bundle);
        return stockFragment;
    }
}
