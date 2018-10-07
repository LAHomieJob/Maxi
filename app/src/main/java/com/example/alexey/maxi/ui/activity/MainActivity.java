package com.example.alexey.maxi.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alexey.maxi.R;
import com.example.alexey.maxi.di.DI;
import com.example.alexey.maxi.model.StockItem;
import com.example.alexey.maxi.ui.adapter.AdapterStockList;
import com.example.alexey.maxi.util.ExtensionsKt;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @Inject
    @InjectPresenter
    MainPresenter presenter;
    private RecyclerView list;
    private LinearLayoutManager layoutManager;
    private AdapterStockList adapter;

    @ProvidePresenter
    MainPresenter providePresenter(){
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DI.INSTANCE.componentManager().getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list_item);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void showStocks(@NotNull List<StockItem> stockItems) {
        adapter = new AdapterStockList(stockItems);
        list.setAdapter(adapter);
        list.setLayoutManager(layoutManager);
    }

    @Override
    public void showError(@NotNull String errorMsg) {
        ExtensionsKt.toast(this, errorMsg);
    }
}
