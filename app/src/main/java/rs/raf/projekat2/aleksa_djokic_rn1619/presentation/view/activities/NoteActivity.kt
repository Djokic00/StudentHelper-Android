package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.projekat2.aleksa_djokic_rn1619.R
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityMainBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners();
    }

    private fun initListeners() {
        binding.addNoteBtn.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivity(intent)
        }
    }
}