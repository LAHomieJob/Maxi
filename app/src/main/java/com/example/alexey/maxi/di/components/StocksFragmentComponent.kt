package com.example.alexey.maxi.di.components

import com.example.alexey.maxi.di.modules.local.RubricsFragmentModule
import com.example.alexey.maxi.di.scopes.FragmentScope
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(RubricsFragmentModule::class))
@FragmentScope
interface StocksFragmentComponent {

}