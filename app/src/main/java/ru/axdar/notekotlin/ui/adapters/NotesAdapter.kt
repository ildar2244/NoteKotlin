package ru.axdar.notekotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.axdar.notekotlin.R
import ru.axdar.notekotlin.mvp.models.Note
import ru.axdar.notekotlin.utils.formatDate
import java.util.ArrayList

/**
 * Created by ildar2244 on 24.09.2018.
 */
class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private var mNoteList: List<Note> = ArrayList()

    constructor(mNoteList: List<Note>) : super() {
        this.mNoteList = mNoteList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.note_item_layout, parent, false)
        return NotesAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var note = mNoteList[position]
        holder.mNoteTitle.text = note.title
        holder.mNoteDate.text = formatDate(note.changeDate)
    }

    override fun getItemCount(): Int {
        return mNoteList.size
    }

    class ViewHolder : RecyclerView.ViewHolder {

        var mNoteTitle: TextView
        var mNoteDate: TextView

        constructor(itemView: View) : super(itemView) {
            mNoteTitle = itemView.findViewById(R.id.tvItemNoteTitle) as TextView
            mNoteDate = itemView.findViewById(R.id.tvItemNoteDate) as TextView
        }
    }
}