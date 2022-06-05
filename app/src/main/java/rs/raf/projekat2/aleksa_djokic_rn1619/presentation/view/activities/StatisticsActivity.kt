package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.joda.time.DateTime
import org.joda.time.Days
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.aleksa_djokic_rn1619.R
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.composable.RafBlue
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract.StudentContract
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.NoteState
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel.StudentViewModel
import timber.log.Timber
import java.util.*
import kotlin.math.absoluteValue


class StatisticsActivity : AppCompatActivity() {
    private val studentViewModel: StudentContract.ViewModel by viewModel<StudentViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        studentViewModel.noteState.observe(this) {
            getAllNotes(it)
        }
        studentViewModel.getAllNotes()
    }

    private fun getAllNotes(state: NoteState) {
        val currentDate = Date()
        val noteHistoryList = mutableListOf(0, 0, 0, 0, 0)
        when (state) {
            is NoteState.Success -> {
                Timber.e("Success")
                state.notes.sortedBy { it.date_of_creation }
                state.notes.forEach {
                    val currentDt = DateTime(currentDate)
                    val noteDt = DateTime(it.date_of_creation)
                    when (Days.daysBetween(currentDt.toLocalDate(), noteDt.toLocalDate()).days.absoluteValue) {
                        0 -> noteHistoryList[0] += 1
                        1 -> noteHistoryList[1] += 1
                        2 -> noteHistoryList[2] += 1
                        3 -> noteHistoryList[3] += 1
                        4 -> noteHistoryList[4] += 1
                    }
                }
                println(noteHistoryList)
                println(state.notes)
            }
            is NoteState.Error -> {
                Timber.e("Error")
            }
            is NoteState.DataFetched -> {
                Timber.e("Data fetched")
            }
            is NoteState.Loading -> {
                Timber.e("Loading")
            }
        }
        setContent {
            makeChart(noteHistoryList)
        }
    }

    @Composable
    fun makeChart(noteHistoryList: List<Int>) {
        Box(
            modifier = Modifier.padding(30.dp, 50.dp)
        ) {
            Text(
                text = stringResource(R.string.statistics),
                fontSize = 30.sp,
                color = RafBlue,
                textAlign = TextAlign.Center,
            )
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            rotate(degrees = 180F) {
                drawRect( // Prvo crtamo za 5ti dan (tj. danasnji dan)
                    color = RafBlue,
                    size = Size(width = 120f, height = noteHistoryList[0] * 50f),
                    topLeft = Offset(x = 225f, y = 700f)
                )
                drawRect(
                    color = RafBlue,
                    size = Size(width = 120f, height = noteHistoryList[1] * 50f),
                    topLeft = Offset(x = 350f, y = 700f)
                )
                drawRect(
                    color = RafBlue,
                    size = Size(width = 120f, height = noteHistoryList[2] * 50f),
                    topLeft = Offset(x = 475f, y = 700f)
                )
                drawRect(
                    color = RafBlue,
                    size = Size(width = 120f, height = noteHistoryList[3] * 50f),
                    topLeft = Offset(x = 600f, y = 700f)
                )
                drawRect(
                    color = RafBlue,
                    size = Size(width = 120f, height = noteHistoryList[4] * 50f),
                    topLeft = Offset(x = 725f, y = 700f)
                )
            }
        }
    }
}