package com.colemichaels.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.colemichaels.notes.databinding.FragmentNotesListBinding
import com.colemichaels.notes.dummy.DummyContent
import com.colemichaels.notes.utilities.PrefsHelper

class NotesFragment : Fragment(),
        NotesRecyclerViewAdapter.NoteItemListener {

    private lateinit var binding: FragmentNotesListBinding
    private lateinit var adapter: NotesRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentNotesListBinding.inflate(layoutInflater, container, false)
        val layoutStyle = PrefsHelper.getItemType(requireContext())
        binding.list.layoutManager = if (layoutStyle == "grid") {
            LinearLayoutManager(requireContext())
        } else {
            LinearLayoutManager(requireContext())
        }
        adapter = NotesRecyclerViewAdapter(DummyContent.ITEMS, this)
        binding.list.adapter = adapter
        return binding.root
    }

    override fun onNoteItemLongClick(item: DummyContent.DummyItem): Boolean {
        Log.i("NotesFragment", "Note Clicked ${item.id}")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_grid_view -> {
                PrefsHelper.setItemType(requireContext(), "grid")
                binding.list.layoutManager = GridLayoutManager(context, 2)
                binding.list.adapter = adapter
            }
        }
        return true
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            NotesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

}