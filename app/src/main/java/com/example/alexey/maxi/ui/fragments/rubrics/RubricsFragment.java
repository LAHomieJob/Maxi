package com.example.alexey.maxi.ui.fragments.rubrics;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alexey.maxi.R;
import com.example.alexey.maxi.di.DI;
import com.example.alexey.maxi.di.components.RubricsFragmentComponent;
import com.example.alexey.maxi.domain.models.RubrickItem;
import com.example.alexey.maxi.ui.adapter.AdapterRubrics;
import com.example.alexey.maxi.ui.adapter.OnClick;
import com.example.alexey.maxi.ui.fragments.stock.StockFragment;
import com.example.alexey.maxi.util.ExtensionsKt;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

public class RubricsFragment extends MvpAppCompatFragment implements RubricsView, OnClick {

    @Inject
    @InjectPresenter
    RubricsPresenter presenter;
    private RubricsFragmentComponent component;
    private AdapterRubrics adapter;
    private RecyclerView list;
    private LinearLayoutManager layoutManager;

    @ProvidePresenter
    RubricsPresenter providePresenter() {
        return presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        component = DI.INSTANCE.componentManager().provideRubricsFragmentComponent();
        component.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rubrics_fragment, null);
        list = view.findViewById(R.id.list_item);
        layoutManager = new LinearLayoutManager
                (getContext(), LinearLayoutManager.VERTICAL, false);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        component = null;
    }

    @Override
    public void showListOfRubrics(@NotNull List<RubrickItem> items) {
        adapter = new AdapterRubrics(this, items);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }

    @Override
    public void showError(@NotNull String message) {
        ExtensionsKt.toast(getContext(), message);
    }

    @Override
    public void onItemClick(int position) {
        adapter.getList();
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_container, StockFragment.createInstance(rubricId))
                .commit();
    }
}
