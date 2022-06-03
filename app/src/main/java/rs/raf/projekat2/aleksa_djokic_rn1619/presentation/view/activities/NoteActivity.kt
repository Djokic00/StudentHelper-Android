package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.aleksa_djokic_rn1619.data.models.Note
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
//        adapter = NoteAdapter {item ->
////            println(item.id)
////            println(item.title)
//        }
//        adapter = NoteAdapter{item ->
//            print(item.id)
//        }
        binding.noteRv.adapter = adapter
    }



    private fun renderState(state: NoteState) {
        when (state) {
            is NoteState.Success -> {
                showLoadingState(false)
                Timber.e("Success")
                adapter.submitList(state.notes)
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