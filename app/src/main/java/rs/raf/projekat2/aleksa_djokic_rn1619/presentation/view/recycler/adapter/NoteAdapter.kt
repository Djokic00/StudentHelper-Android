package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNoteTemplateBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.diff.NoteDiffItemCallback
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.viewholder.NoteViewHolder

class NoteAdapter(
    private val details: (Note) -> Unit,
    private val trashClicked: (Note) -> Unit
): ListAdapter<Note, NoteViewHolder>(NoteDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemBinding = ActivityNoteTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(itemBinding, {
            details(getItem(it))
        }) {
            trashClicked(getItem(it))
        }
    }
//class NoteAdapter: ListAdapter<Note, NoteViewHolder>(NoteDiffItemCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
//        val itemBinding = ActivityNoteTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return NoteViewHolder(itemBinding) {}
//    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}