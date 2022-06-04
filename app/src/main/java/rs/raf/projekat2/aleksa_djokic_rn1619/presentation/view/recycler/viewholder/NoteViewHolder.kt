package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.viewholder


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteResponse
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNoteTemplateBinding

class NoteViewHolder(
    private val itemBinding: ActivityNoteTemplateBinding,
    details: (Int) -> Unit,
    trashClicked: (Int) -> Unit,
    editClicked: (Int) -> Unit,
    stateClicked: (Int) -> Unit,
) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.root.setOnClickListener { details(bindingAdapterPosition) }
        itemBinding.trashImg.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                trashClicked(bindingAdapterPosition)
            }
        }
        itemBinding.editImg.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                editClicked(bindingAdapterPosition)
            }
        }
        itemBinding.archiveImg.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                stateClicked(bindingAdapterPosition)
            }
        }
        itemBinding.unarchiveImg.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                stateClicked(bindingAdapterPosition)
            }
        }
    }

    fun bind(note: NoteResponse) {
        itemBinding.noteTitleTv.text = note.title
        itemBinding.noteDescriptionTv.text = note.content
        itemBinding.trashImg
        itemBinding.editImg
        if (note.archive) {
            println("USAO U TRUE")
            itemBinding.archiveImg.visibility = View.GONE
            itemBinding.unarchiveImg.visibility = View.VISIBLE
        } else {
            println("USAO U FALSE")
            itemBinding.unarchiveImg.visibility = View.GONE
            itemBinding.archiveImg.visibility = View.VISIBLE
        }
    }
}