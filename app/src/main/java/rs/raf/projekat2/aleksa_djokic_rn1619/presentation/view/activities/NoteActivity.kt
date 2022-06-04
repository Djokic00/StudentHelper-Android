package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.NoteResponse
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNoteBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.contract.StudentContract
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.recycler.adapter.NoteAdapter
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.states.NoteState
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.viewmodel.StudentViewModel
import timber.log.Timber

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private val studentViewModel: StudentContract.ViewModel by viewModel<StudentViewModel>()
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initObservers()
        initRecycler()
    }

    private fun initListeners() {
        binding.searchNoteEt.doAfterTextChanged {
            val text = binding.searchNoteEt.text.toString()
            println(text)
            studentViewModel.getNoteByFilter(text)
        }

        binding.switch1.setOnCheckedChangeListener { _, _ ->
            studentViewModel.getAllNotes()
        }

        binding.addNoteBtn.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initObservers() {
        studentViewModel.noteState.observe(this) {
            renderState(it)
        }
        studentViewModel.getAllNotes()
    }

    private fun initRecycler() {
        binding.noteRv.layoutManager = LinearLayoutManager(this)
        adapter = NoteAdapter(
            {item ->
                println(item.title)
            },
            {item -> studentViewModel.deleteNote(item.id)},
            {item ->
                val intent = Intent(this, EditNoteActivity::class.java)
                intent.putExtra("NOTE", item)
                startActivity(intent)
            },
            { item ->
                studentViewModel.changeNoteState(item.id)
            }
        )
        binding.noteRv.adapter = adapter
    }

    private fun renderState(state: NoteState) {
        val listOfNotes: List<NoteResponse>
        when (state) {
            is NoteState.Success -> {
                showLoadingState(false)
                Timber.e("Success")
                listOfNotes = if (!binding.switch1.isChecked) {
                    state.notes.filter {
                        !it.archive
                    }
                } else state.notes
                println(listOfNotes)
                adapter.submitList(listOfNotes)
            }
            is NoteState.Error -> {
                showLoadingState(false)
                Timber.e("Error")
            }
            is NoteState.DataFetched -> {
                showLoadingState(false)
                Timber.e("Data fetched")
            }
            is NoteState.Loading -> {
                showLoadingState(true)
                Timber.e("Loading")
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.searchNoteEt.isVisible = !loading
        binding.switch1.isVisible = !loading
        binding.addNoteBtn.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }
}