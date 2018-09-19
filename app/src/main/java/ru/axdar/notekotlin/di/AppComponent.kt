package ru.axdar.notekotlin.di

import dagger.Component
import ru.axdar.notekotlin.mvp.presenters.MainPresenter
import javax.inject.Singleton

/**
 * Created by ildar2244 on 19.09.2018.
 */
@Singleton
@Component(modules = arrayOf(NoteDaoModule::class))
interface AppComponent {

    fun inject(mainPresenter: MainPresenter)
}