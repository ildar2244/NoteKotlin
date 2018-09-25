package ru.axdar.notekotlin.ui

import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.arellomobile.mvp.presenter.InjectPresenter
import com.pawegio.kandroid.onQueryChange
import ru.axdar.notekotlin.R
import ru.axdar.notekotlin.mvp.common.MvpAppCompatActivity
import ru.axdar.notekotlin.mvp.models.Note
import ru.axdar.notekotlin.mvp.presenters.MainPresenter
import ru.axdar.notekotlin.mvp.views.MainView
import ru.axdar.notekotlin.ui.adapters.NotesAdapter
import ru.axdar.notekotlin.ui.commons.ItemClickSupport

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mPresenter: MainPresenter
    private var mNoteContextDialog: MaterialDialog? = null
    private var mNoteDeleteDialog: MaterialDialog? = null
    private var mNoteInfoDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(ItemClickSupport.addTo(rvNotesList)) {
            setOnItemClickListener { _, position, _ -> mPresenter.openNote(this@MainActivity, position) }
            setOnItemLongClickListener { _, position, _ ->
                mPresenter.showNoteContextDialog(position); true }
        }

        fabButton.attachToRecyclerView(rvNotesList)
        fabButton.setOnClickListener { mPresenter.openNewNote(this) }
    }

    override fun onNotesLoaded(notes: List<Note>) {
        rvNotesList.adapter = NotesAdapter(notes)
        updateView()
    }

    override fun updateView() {
        rvNotesList.adapter.notifyDataSetChanged()
        if (rvNotesList.adapter.itemCount == 0) {
            rvNotesList.visibility = View.GONE
            tvNotesIsEmpty.visibility = View.VISIBLE
        } else {
            rvNotesList.visibility = View.VISIBLE
            tvNotesIsEmpty.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        initSearchView(menu)
        return true
    }

    private fun initSearchView(menu: Menu) {
        var searchViewMenuItem = menu.findItem(R.id.action_search)
        var searchView = searchViewMenuItem.actionView as SearchView
        searchView.onQueryChange { query -> mPresenter.search(query) }
        searchView.setOnCloseListener { mPresenter.search(""); false }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuDeleteAllNotes -> mPresenter.deleteAllNotes()
            R.id.menuSortByName -> mPresenter.sortNotesBy(MainPresenter.SortNotesBy.NAME)
            R.id.menuSortByDate -> mPresenter.sortNotesBy(MainPresenter.SortNotesBy.DATE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSearchResult(notes: List<Note>) {
        rvNotesList.adapter = NotesAdapter(notes)
    }

    override fun onAllNotesDeleted() {
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
    override fun openNoteScreen(noteId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteDeleteDialog(notePosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNoteDeleteDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteContextDialog(notePosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNoteContextDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
