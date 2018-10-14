package com.example.alexey.maxi.presentation.mainScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.alexey.maxi.R;
import com.example.alexey.maxi.di.DI;
import com.example.alexey.maxi.presentation.base.BaseActivity;
import com.example.alexey.maxi.presentation.base.BaseFragment;
import com.example.alexey.maxi.presentation.navigation.ScreenKeys;
import com.example.alexey.maxi.presentation.rubricsScreen.view.RubricsFragment;
import com.example.alexey.maxi.presentation.stocksScreen.view.StockFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportAppNavigator;

public class MainActivity extends BaseActivity {

    //Так как MainActivty является корневой и не обновляет View, то мы не следуем паттерну MVP и
    //инъицируем роутер прямо во View
    @Inject
    Router router;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DI.INSTANCE.componentManager().getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        //Переход на стартовый фрагмент экрана "Рубрики" осуществляется при первом запуске MainActivity
        if (savedInstanceState == null) {
            router.navigateTo(ScreenKeys.RUBRICS_SCREEN);
        }
    }

    @NotNull
    @Override
    protected Navigator getNavigator() {
        return new SupportAppNavigator(this, fragmentManager, R.id.frame_container) {
            @Override
            protected Intent createActivityIntent(Context context, String screenKey, Object data) {
                return null;
            }

            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                switch (screenKey) {
                    case ScreenKeys.RUBRICS_SCREEN:
                        return new RubricsFragment();
                    case ScreenKeys.STOCK_SCREEN:
                        return StockFragment.createInstance((Integer) data);
                    default:
                        throw new IllegalArgumentException("Несуществующий ключ экрана");
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        BaseFragment fragment = (BaseFragment)
                getSupportFragmentManager().findFragmentById(R.id.frame_container);
        //На экране "Рубрики" при нажатии кнопки Назад завершить приложение
        if (fragment instanceof RubricsFragment) {
            finish();
        } else {
            fragment.onBackPressed();
        }
    }
}
