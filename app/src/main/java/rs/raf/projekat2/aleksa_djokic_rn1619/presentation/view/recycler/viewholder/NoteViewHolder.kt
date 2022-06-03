package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.viewholder

import android.media.Image
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNoteTemplateBinding

class NoteViewHolder(
    private val itemBinding: ActivityNoteTemplateBinding,
    details: (Int) -> Unit,
    trashClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.root.setOnClickListener { details(bindingAdapterPosition) }
        itemBinding.trashImg.setOnClickListener { view: View? ->
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                trashClicked(bindingAdapterPosition)
            }
        }
    }

    fun bind(note: Note) {
        itemBinding.noteTitleTv.text = note.title
        itemBinding.noteDescriptionTv.text = note.content
        itemBinding.trashImg
        itemBinding.editImg
        itemBinding.archiveImg
    }
}