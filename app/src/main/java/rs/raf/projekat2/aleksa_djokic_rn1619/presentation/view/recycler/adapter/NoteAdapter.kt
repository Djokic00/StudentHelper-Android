package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteResponse
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNoteTemplateBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.diff.NoteDiffItemCallback
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.viewholder.NoteViewHolder

class NoteAdapter(
    private val details: (NoteResponse) -> Unit,
    private val trashClicked: (NoteResponse) -> Unit,
    private val editClicked: (NoteResponse) -> Unit,
    private val stateClicked: (NoteResponse) -> Unit,
): ListAdapter<NoteResponse, NoteViewHolder>(NoteDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemBinding = ActivityNoteTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(itemBinding,
            { details(getItem(it)) },
            { trashClicked(getItem(it)) },
            { editClicked(getItem(it)) },
            { stateClicked(getItem(it)) }
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}