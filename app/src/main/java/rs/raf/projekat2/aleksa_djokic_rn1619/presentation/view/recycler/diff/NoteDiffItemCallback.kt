package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note

class NoteDiffItemCallback: DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == newItem.title
                && oldItem.content == newItem.content
    }
}