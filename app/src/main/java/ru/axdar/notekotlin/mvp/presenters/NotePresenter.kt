package ru.axdar.notekotlin.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.greenrobot.eventbus.EventBus
import ru.axdar.notekotlin.NoteApp
import ru.axdar.notekotlin.bus.NoteDeleteAction
import ru.axdar.notekotlin.bus.NoteEditAction
import ru.axdar.notekotlin.mvp.models.Note
import ru.axdar.notekotlin.mvp.models.NoteDao
import ru.axdar.notekotlin.mvp.views.NoteView
import java.util.*
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

    init {
        NoteApp.graph.inject(this)
    }

    fun showNote(noteId: Long, notePosition: Int) {
        mNotePosition = notePosition
        mNote = mNoteDao.getNoteById(noteId)
        viewState.showNote(mNote)
    }

    fun saveNote(title: String, text: String) {
        mNote.title = title
        mNote.text = text
        mNote.changeDate = Date()
        mNoteDao.saveNote(mNote)
        EventBus.getDefault().post(NoteEditAction(mNotePosition))
        viewState.onNoteSaved()
    }

    fun deleteNote() {
        mNoteDao.deleteNote(mNote)
        EventBus.getDefault().post(NoteDeleteAction(mNotePosition))
        viewState.onNoteDeleted()
    }

    fun showNoteDeleteDialog() {
        viewState.showNoteDeleteDialog()
    }

    fun hideNoteDeleteDialog() {
        viewState.hideNoteDeleteDialog()
    }

    fun showNoteInfoDialog() {
        viewState.showNoteInfoDialog(mNote.getInfo())
    }

    fun hideNoteInfoDialog() {
        viewState.hideNoteInfoDialog()
    }
}