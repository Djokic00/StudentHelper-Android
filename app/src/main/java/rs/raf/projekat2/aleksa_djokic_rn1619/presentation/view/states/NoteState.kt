package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states

import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteResponse

sealed class NoteState {
    object Loading: NoteState()
    object DataFetched: NoteState()
    data class Success(val notes: List<NoteResponse>): NoteState()
    data class Error(val message: String): NoteState()
}
