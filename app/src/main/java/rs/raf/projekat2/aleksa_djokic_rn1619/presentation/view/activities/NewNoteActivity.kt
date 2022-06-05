package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.joda.time.DateTime
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNewNoteBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract.StudentContract
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel.StudentViewModel
import java.util.*


class NewNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewNoteBinding
    private val studentViewModel: StudentContract.ViewModel by viewModel<StudentViewModel>()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        val currentDate = Date()
        val dtOriginal: DateTime = DateTime(currentDate)
        dtOriginal.minusDays(1)

        binding.saveBtn.setOnClickListener {
            val title = binding.noteTitleEt.text.toString()
            val content = binding.noteContentEt.text.toString()
            val newNote = Note(
                id = 0,
                title = title,
                content = content,
                archive = false,
                date = currentDate
            )
            studentViewModel.saveNote(newNote)
            finish()
        }

        binding.cancelBtn.setOnClickListener {
            finish()
        }
    }
}