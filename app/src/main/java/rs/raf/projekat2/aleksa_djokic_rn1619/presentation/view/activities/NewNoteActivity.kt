package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNewNoteBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNoteBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract.StudentContract
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel.StudentViewModel

class NewNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewNoteBinding
    private val studentViewModel: StudentContract.ViewModel by viewModel<StudentViewModel>()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners();
    }

    private fun initListeners() {
        binding.saveBtn.setOnClickListener {
            val title = binding.noteTitleEt.text.toString()
            val content = binding.noteContentEt.text.toString()
            val newNote = Note(
                id = 0,
                title = title,
                content = content
            )
            studentViewModel.saveNote(newNote)
            finish()
        }

        binding.cancelBtn.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}