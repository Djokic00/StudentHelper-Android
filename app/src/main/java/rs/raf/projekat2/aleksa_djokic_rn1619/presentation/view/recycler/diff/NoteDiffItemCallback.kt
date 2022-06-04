package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteResponse

class NoteDiffItemCallback: DiffUtil.ItemCallback<NoteResponse>() {

    override fun areItemsTheSame(oldItem: NoteResponse, newItem: NoteResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteResponse, newItem: NoteResponse): Boolean {
        return oldItem.title == newItem.title
                && oldItem.content == newItem.content
    }
}