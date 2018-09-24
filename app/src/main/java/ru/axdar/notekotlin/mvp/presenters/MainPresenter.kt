package ru.axdar.notekotlin.mvp.presenters

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.greenrobot.eventbus.Subscribe
import ru.axdar.notekotlin.mvp.models.Note
import ru.axdar.notekotlin.mvp.models.NoteDao
import ru.axdar.notekotlin.mvp.views.MainView
import ru.axdar.notekotlin.ui.NoteActivity
import ru.axdar.notekotlin.utils.getNotesSortMethodName
import ru.axdar.notekotlin.utils.setNotesSortMethod
import java.util.*
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadAllNotes()
    }

    fun loadAllNotes() {
        mNotesList = mNoteDao.loadAllNotes()
        Collections.sort(mNotesList, getCurrentSortMethod())
        viewState.onNotesLoaded(mNotesList)
    }

    fun getCurrentSortMethod(): SortNotesBy {
        val defaultSortMethodName = SortNotesBy.DATE.toString()
        val currentSortMethodName = getNotesSortMethodName(defaultSortMethodName)
        return SortNotesBy.valueOf(currentSortMethodName)
    }

    fun deleteAllNotes() {
        mNoteDao.deleteAllNotes()
        mNotesList.removeAll(mNotesList)
        viewState.onAllNotesDeleted()
    }

    fun deleteNoteByPosition(position: Int) {
        val note = mNotesList[position]
        mNoteDao.deleteNote(note)
        mNotesList.remove(note)
        viewState.onNoteDeleted()
    }

    fun openNewNote(activity: Activity) {
        val newNote = mNoteDao.createNote()
        mNotesList.add(newNote)
        sortNotesBy(getCurrentSortMethod())
        openNote(activity, mNotesList.indexOf(newNote))
    }

    fun openNote(activity: Activity, position: Int) {
        val intent = Intent(activity, NoteActivity::class.java)
        intent.putExtra("note_position", position)
        intent.putExtra("note_id", mNotesList[position].id)
        activity.startActivity(intent)
    }

    fun search(query: String) {
        if (query == "") {
            viewState.onSearchResult(mNotesList)
        } else {
            val searchResults = mNotesList.filter { it.title!!.startsWith(query, ignoreCase = true) }
            viewState.onSearchResult(searchResults)
        }
    }

    fun sortNotesBy(sortMethod: SortNotesBy) {
        mNotesList.sortWith(sortMethod)
        setNotesSortMethod(sortMethod.toString())
        viewState.updateView()
    }

    fun showNoteContextDialog(position: Int) {
        viewState.showNoteContextDialog(position)
    }

    fun hideNoteContextDialog() {
        viewState.hideNoteContextDialog()
    }

    fun showNoteDeleteDialog(position: Int) {
        viewState.showNoteDeleteDialog(position)
    }

    fun hideNoteDeleteDialog() {
        viewState.hideNoteDeleteDialog()
    }

    fun hideNoteInfoDialog() {
        viewState.hideNoteInfoDialog()
    }

}