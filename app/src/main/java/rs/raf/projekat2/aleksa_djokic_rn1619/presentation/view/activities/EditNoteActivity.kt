package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteResponse
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityEditBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract.StudentContract
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel.StudentViewModel
import timber.log.Timber

class EditNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private var note: NoteResponse? = null
    private val studentViewModel: StudentContract.ViewModel by viewModel<StudentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        note = intent.getParcelableExtra("NOTE")
        if (note == null) {
            Timber.e("Note is null")
            finish()
        }
        initView()
        initListeners()
    }

    private fun initView() {
        binding.noteTitleEt.setText(note?.title)
        binding.noteContentEt.setText(note?.content)
    }

    private fun initListeners() {
        binding.editBtn.setOnClickListener {
            val title = binding.noteTitleEt.text.toString()
            val content = binding.noteContentEt.text.toString()
            note?.let { it -> studentViewModel.editNote(it.id, title, content) }
            finish()
        }

        binding.cancelBtn.setOnClickListener {
            finish()
        }

    }
}