package com.example.alexey.maxi.presentation.stocksScreen.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alexey.maxi.R;
import com.example.alexey.maxi.di.DI;
import com.example.alexey.maxi.di.components.StocksFragmentComponent;
import com.example.alexey.maxi.domain.models.StockItem;
import com.example.alexey.maxi.presentation.base.BaseFragment;
import com.example.alexey.maxi.presentation.stocksScreen.presenter.StockPresenter;
import com.example.alexey.maxi.util.ExtensionsKt;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

public class StockFragment extends BaseFragment implements StockView {

    private static final String PARENT_RUBRIC = "Parent rubric";

    @Inject
    @InjectPresenter
    StockPresenter presenter;
    private StocksFragmentComponent component;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterStockList adapter;
    private Toolbar toolbar;

    @ProvidePresenter
    StockPresenter providePresenter() {
        return presenter;
    }

    public static StockFragment createInstance(int parentRubric) {
        StockFragment stockFragment = new StockFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARENT_RUBRIC, parentRubric);
        stockFragment.setArguments(bundle);
        return stockFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        component = DI.INSTANCE.componentManager().
                provideStockFragmentComponent(getArguments().getInt(PARENT_RUBRIC));
        component.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_fragment, null);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.list_of_stocks);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        recyclerView = view.findViewById(R.id.list_item);
        layoutManager = new LinearLayoutManager
                (getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        presenter.showListOfStocks();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        component = null;
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    public void showStocks(@NotNull List<StockItem> list) {
        adapter = new AdapterStockList(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(@NotNull String errorMsg) {
        ExtensionsKt.toast(getContext(), errorMsg);
    }
}
