package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Subject

class SubjectDiffItemCallback: DiffUtil.ItemCallback<Subject>() {

    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.predmet == newItem.predmet
                && oldItem.tip == newItem.tip
                && oldItem.nastavnik == newItem.nastavnik
                && oldItem.grupe == newItem.grupe
                && oldItem.dan == newItem.dan
                && oldItem.ucionica == newItem.ucionica
                && oldItem.termin == newItem.termin
    }
}