package ru.axdar.notekotlin.di

import dagger.Module
import dagger.Provides
import ru.axdar.notekotlin.mvp.models.NoteDao
import javax.inject.Singleton

/**
 * Created by ildar2244 on 19.09.2018.
 */
@Module
class NoteDaoModule {

    @Provides
    @Singleton
    fun provideNoteDao(): NoteDao = NoteDao()

}