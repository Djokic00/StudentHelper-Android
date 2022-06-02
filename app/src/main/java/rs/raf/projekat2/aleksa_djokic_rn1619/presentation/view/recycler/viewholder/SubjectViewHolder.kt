package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Subject
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivitySubjectTemplateBinding

class SubjectViewHolder (private val itemBinding: ActivitySubjectTemplateBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(subject: Subject) {
        itemBinding.subjectTv.text = subject.predmet
        itemBinding.typeTv.text = subject.tip
        itemBinding.professorTv.text = subject.nastavnik
        itemBinding.classroomTv.text = subject.ucionica
        itemBinding.groupTv.text = subject.grupe
        itemBinding.dayTv.text = subject.dan
        itemBinding.timeTv.text = subject.termin
    }
}