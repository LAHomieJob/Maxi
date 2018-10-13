package com.example.alexey.maxi.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import ru.terrakok.cicerone.Router

open class BasePresenter<V : MvpView>(private val router: Router) : MvpPresenter<V>() {
    open fun onBackPressed() = router.exit();
}
