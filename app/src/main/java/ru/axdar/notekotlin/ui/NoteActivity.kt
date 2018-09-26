package ru.axdar.notekotlin.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import ru.axdar.notekotlin.R

import kotlinx.android.synthetic.main.activity_note.*
import ru.axdar.notekotlin.mvp.common.MvpAppCompatActivity
import ru.axdar.notekotlin.mvp.models.Note
import ru.axdar.notekotlin.mvp.presenters.NotePresenter
import ru.axdar.notekotlin.mvp.views.NoteView
import ru.axdar.notekotlin.utils.formatDate
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
        tvNoteDate.text = formatDate(note.changeDate)
        etTitle.setText(note.title)
        etText.setText(note.text)
    }

    override fun onNoteSaved() {
        Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteDeleted() {
        Toast.makeText(this, R.string.note_is_deleted, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showNoteInfoDialog(noteInfo: String) {
        mNoteInfoDialog = MaterialDialog.Builder(this)
                .title("Информация о заметке")
                .positiveText("Ок")
                .content(noteInfo)
                .onPositive { dialog, action -> mPresenter.hideNoteInfoDialog() }
                .cancelListener { mPresenter.hideNoteInfoDialog() }
                .show()
    }

    override fun hideNoteInfoDialog() {
        mNoteInfoDialog?.dismiss()
    }

    override fun showNoteDeleteDialog() {
        mNoteDeleteDialog = MaterialDialog.Builder(this)
                .title("Удаление заметки")
                .content("Вы действительно хотите удалить заметку")
                .positiveText("Да")
                .negativeText("Нет")
                .onPositive { dialog, action ->
                    mPresenter.hideNoteDeleteDialog()
                    mPresenter.deleteNote()
                }
                .onNegative { dialog, action -> mPresenter.hideNoteDeleteDialog() }
                .cancelListener { mPresenter.hideNoteDeleteDialog() }
                .show()
    }

    override fun hideNoteDeleteDialog() {
        mNoteDeleteDialog?.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuSaveNote -> mPresenter.saveNote(etTitle.text.toString(), etText.text.toString())
            R.id.menuDeleteNote -> mPresenter.showNoteDeleteDialog()
            R.id.menuNoteInfo -> mPresenter.showNoteInfoDialog()
        }
        return super.onOptionsItemSelected(item)
    }
}
