package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteResponse
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.NoteState
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.SubjectState

interface StudentContract {
    interface ViewModel {

        val noteState: LiveData<NoteState>
        val subjectState: LiveData<SubjectState>
        fun fetchAllSubjects()
        fun getAllSubjects()
        fun filterSubject(group: String, day: String, text: String)
        fun getAllNotes()
        fun editNote(id: Int, newTitle: String, newContent: String)
        fun saveNote(note: Note)
        fun deleteNote(id: Int)
        fun changeNoteState(id: Int)
        fun getNoteByFilter(text: String)
        fun filterWithNull(group: String, day: String, text: String)
    }
}