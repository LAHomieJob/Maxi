package com.example.alexey.maxi.di.components

import com.example.alexey.maxi.di.modules.rubricsScreen.RubricsFragmentModule
import com.example.alexey.maxi.di.scopes.FragmentScope
import com.example.alexey.maxi.presentation.rubricsScreen.view.RubricsFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(RubricsFragmentModule::class))
@FragmentScope
interface RubricsFragmentComponent {
    fun inject(fragment: RubricsFragment)
}