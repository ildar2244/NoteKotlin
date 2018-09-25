package ru.axdar.notekotlin.mvp.views

import com.arellomobile.mvp.MvpView
import ru.axdar.notekotlin.mvp.models.Note

/**
 * Created by ildar2244 on 25.09.2018.
 */
interface NoteView : MvpView {

    fun showNote(note: Note)

    fun onNoteSaved()

    fun onNoteDeleted()

    fun showNoteInfoDialog(noteInfo: String)

    fun hideNoteInfoDialog()

    fun showNoteDeleteDialog()

    fun hideNoteDeleteDialog()
}