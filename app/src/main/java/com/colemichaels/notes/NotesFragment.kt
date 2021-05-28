package com.colemichaels.notes

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.colemichaels.notes.databinding.FragmentNotesListBinding
import com.colemichaels.notes.dummy.DummyContent
import com.colemichaels.notes.utilities.PrefsHelper

class NotesFragment : Fragment(),
        NotesRecyclerViewAdapter.NoteItemListener,
        Toolbar.OnMenuItemClickListener {

    private lateinit var binding: FragmentNotesListBinding
    private lateinit var adapter: NotesRecyclerViewAdapter
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesListBinding.inflate(layoutInflater, container, false)
        adapter = NotesRecyclerViewAdapter(DummyContent.ITEMS, this)
        setHasOptionsMenu(true)
        setToolbar()
        setLayoutManager()
        return binding.root
    }

    override fun onNoteItemLongClick(item: DummyContent.DummyItem): Boolean {
        Log.i("NotesFragment", "Note Clicked ${item.id}")
        return true
    }

    private fun setLayoutManager() {
        val layoutStyle = PrefsHelper.getItemType(requireContext())
        val gridMenuItemText = toolbar.menu.findItem(R.id.action_grid_view)
        if (layoutStyle == "grid") {
            gridMenuItemText.title = getString(R.string.action_list_view)
            binding.list.layoutManager = GridLayoutManager(requireContext(), 2)
        } else {
            gridMenuItemText.title = getString(R.string.action_grid_view)
            binding.list.layoutManager = LinearLayoutManager(requireContext())
        }
        binding.list.adapter = adapter
    }

    private fun setToolbar() {
        toolbar = activity?.findViewById(R.id.toolbar)!!
        toolbar.let {
            it.title = "Notes"
            it.inflateMenu(R.menu.menu_main)
            it.setOnMenuItemClickListener(this)
        }
    }

    private fun toggleGridView() {
        val layoutKey = if (PrefsHelper.getItemType(requireContext()) == "grid") "list" else "grid"
        PrefsHelper.setItemType(requireContext(), layoutKey)
        setLayoutManager()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_grid_view -> toggleGridView()
        }
        return true
    }

}