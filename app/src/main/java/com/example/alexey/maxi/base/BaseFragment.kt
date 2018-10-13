package com.example.alexey.maxi.base

import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment() {

    companion object {
        const val EXTRA_NAME = "Extra name"
    }

    abstract fun onBackPressed()
}

