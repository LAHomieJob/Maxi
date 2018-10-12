package com.example.alexey.maxi.di.components

import com.example.alexey.maxi.di.scopes.FragmentScope
import com.example.alexey.maxi.ui.fragments.rubrics.RubricsFragment
import dagger.Subcomponent

@Subcomponent
@FragmentScope
interface RubricsFragmentComponent {
    fun inject(fragment: RubricsFragment)
}