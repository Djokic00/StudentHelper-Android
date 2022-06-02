package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.NoteState
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.SubjectState

interface StudentContract {
    interface ViewModel {

        val noteState: LiveData<NoteState>
        val subjectState: LiveData<SubjectState>
//        val savedRecipeState: LiveData<RecipeState>

        fun fetchAllSubjects()
        fun getAllSubjects()
        fun getAllGroupsAndDays()
//        fun filterSubjectByGroup()
//        fun filterSubjectByGroupAndDay()
        fun getAllNotes()
        fun editNote(id: Int)
        fun saveNote(note: Note)
        fun deleteNote(id: Int)

    }
}