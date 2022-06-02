package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Subject
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivitySubjectTemplateBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.diff.SubjectDiffItemCallback
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.viewholder.SubjectViewHolder

class SubjectAdapter: ListAdapter<Subject, SubjectViewHolder>(SubjectDiffItemCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val itemBinding = ActivitySubjectTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}