package ru.axdar.notekotlin

import android.content.Context
import com.activeandroid.app.Application
import ru.axdar.notekotlin.di.AppComponent
import ru.axdar.notekotlin.di.DaggerAppComponent
import ru.axdar.notekotlin.di.NoteDaoModule
import ru.axdar.notekotlin.utils.initPrefs

/**
 * Created by ildar2244 on 19.09.2018.
 */

class NoteApp : Application() {

    companion object {
        lateinit var graph: AppComponent
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        initPrefs(this)

        context = this
        graph = DaggerAppComponent.builder().noteDaoModule(NoteDaoModule()).build()
    }
}