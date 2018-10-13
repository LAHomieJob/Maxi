package com.example.alexey.maxi.presentation.rubricsScreen.view;

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
import com.example.alexey.maxi.di.components.RubricsFragmentComponent;
import com.example.alexey.maxi.domain.models.Rubric;
import com.example.alexey.maxi.presentation.base.BaseFragment;
import com.example.alexey.maxi.presentation.rubricsScreen.presenter.RubricsPresenter;
import com.example.alexey.maxi.util.ExtensionsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.inject.Inject;

public class RubricsFragment extends BaseFragment implements RubricsView {

    @Inject
    @InjectPresenter
    RubricsPresenter presenter;
    private RubricsFragmentComponent component;
    private AdapterRubrics adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Toolbar toolbar;

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
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        recyclerView = view.findViewById(R.id.list_item);
        layoutManager = new LinearLayoutManager
                (getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        presenter.showParentRubrics();
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        component = null;
    }

    @Override
    public void showListOfRubrics(@Nullable List<Rubric> list) {
        adapter = new AdapterRubrics(rubric -> {
            presenter.navigateToStockScreen(rubric.getId());
            return null;
        }, list);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void showError(@NotNull String message) {
        ExtensionsKt.toast(getContext(), message);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
