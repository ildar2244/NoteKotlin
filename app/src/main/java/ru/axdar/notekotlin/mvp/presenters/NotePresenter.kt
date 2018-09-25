package ru.axdar.notekotlin.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.axdar.notekotlin.NoteApp
import ru.axdar.notekotlin.mvp.models.Note
import ru.axdar.notekotlin.mvp.models.NoteDao
import ru.axdar.notekotlin.mvp.views.NoteView
import javax.inject.Inject

/**
 * Created by ildar2244 on 25.09.2018.
 */
@InjectViewState
class NotePresenter : MvpPresenter<NoteView>() {

    @Inject
    lateinit var mNoteDao: NoteDao
    lateinit var mNote: Note
    var mNotePosition: Int = -1

    fun showNote(noteId: Long, notePosition: Int) {
        mNotePosition = notePosition
        mNote = mNoteDao.getNoteById(noteId)
        viewState.showNote(mNote)
    }
}