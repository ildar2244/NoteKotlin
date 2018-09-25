package ru.axdar.notekotlin.ui

import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import ru.axdar.notekotlin.R

import kotlinx.android.synthetic.main.activity_note.*
import ru.axdar.notekotlin.mvp.common.MvpAppCompatActivity
import ru.axdar.notekotlin.mvp.models.Note
import ru.axdar.notekotlin.mvp.presenters.NotePresenter
import ru.axdar.notekotlin.mvp.views.NoteView
import javax.inject.Inject

class NoteActivity : MvpAppCompatActivity(), NoteView {

    @Inject
    lateinit var mPresenter: NotePresenter
    private var mNoteDeleteDialog: MaterialDialog? = null
    private var mNoteInfoDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val noteId = intent.extras.getLong("note_id", -1)
        val notePosition = intent.extras.getInt("note_position", -1)
        mPresenter.showNote(noteId, notePosition)
    }

    override fun showNote(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNoteSaved() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNoteDeleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteInfoDialog(noteInfo: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNoteInfoDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteDeleteDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNoteDeleteDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
