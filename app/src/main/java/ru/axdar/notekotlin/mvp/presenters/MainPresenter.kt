package ru.axdar.notekotlin.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.axdar.notekotlin.mvp.models.Note
import ru.axdar.notekotlin.mvp.models.NoteDao
import ru.axdar.notekotlin.mvp.views.MainView
import javax.inject.Inject

/**
 * Created by ildar2244 on 18.09.2018.
 */
@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    enum class SortNotesBy : Comparator<Note> {
        DATE {
            override fun compare(o1: Note, o2: Note) = o1.changeDate!!.compareTo(o2.changeDate)
        },
        NAME {
            override fun compare(o1: Note, o2: Note) = o1.title!!.compareTo(o2.title!!)
        }
    }

    @Inject
    lateinit var mNoteDao: NoteDao
    lateinit var mNotesList: MutableList<Note>



}