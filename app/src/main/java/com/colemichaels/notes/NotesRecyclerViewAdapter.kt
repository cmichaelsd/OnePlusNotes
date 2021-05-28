package com.colemichaels.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.colemichaels.notes.dummy.DummyContent.DummyItem
import com.colemichaels.notes.utilities.PrefsHelper
import java.time.format.DateTimeFormatter
import java.util.*

class NotesRecyclerViewAdapter(
        private val values: List<DummyItem>,
        private val itemListener: NoteItemListener
) : RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutStyle = PrefsHelper.getItemType(parent.context)
        val layoutId = if (layoutStyle == "grid") R.layout.fragment_notes_item_grid else R.layout.fragment_notes_item_list
        val view = LayoutInflater.from(parent.context)
                .inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.updatedAt.text = item.updatedAt.format(DateTimeFormatter.ofPattern("MMM dd, HH:mm a", Locale.getDefault())).toString()
        holder.itemView.setOnLongClickListener {
            onLongClick(holder)
        }
        holder.checkbox.setOnClickListener {
            onClick(holder)
        }
    }

    private fun onLongClick(holder: ViewHolder): Boolean {
        holder.checkbox.visibility = View.VISIBLE
        return true
    }

    private fun onClick(holder: ViewHolder) {
        holder.checkbox.visibility = View.GONE
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val updatedAt: TextView = view.findViewById(R.id.updated_at)
        val checkbox: ImageView = view.findViewById(R.id.checkbox)
    }

    interface NoteItemListener {
        fun onNoteItemLongClick(item: DummyItem): Boolean
    }
}