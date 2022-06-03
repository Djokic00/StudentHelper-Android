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

        // Metode koje su za predmet i profesor idu zajedno ili neka druga fora?
        fun filterSubjectsByGroup(group: String)
        fun filterSubjectByDay(day: String)
        fun filterSubjectByGroupAndDay(group: String, day: String)
        fun filterSubjectByText(text: String) // i za profesora i za predmet?

//        fun filterSubjectByGroupDayProfessor(group: String, day: String, professor: String)
//        fun filterSubjectByGroupDaySubject(group: String, day: String, subject: String)
//        fun filterSubjectByDayAndProfessor(group: String, day: String, professor: String)
//        fun filterSubjectByDayAndSubject(group: String, day: String, professor: String)
//        fun filterSubjectBySubject(group: String, day: String, professor: String)

        fun getAllNotes()
        fun editNote(id: Int)
        fun saveNote(note: Note)
        fun deleteNote(id: Int)

    }
}