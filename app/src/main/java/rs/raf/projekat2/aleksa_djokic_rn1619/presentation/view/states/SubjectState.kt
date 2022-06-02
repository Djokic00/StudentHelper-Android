package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states

import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Subject

sealed class SubjectState {
    object Loading: SubjectState()
    object DataFetched: SubjectState()
    data class Success(val subject: List<Subject>): SubjectState()
    data class Error(val message: String): SubjectState()
}