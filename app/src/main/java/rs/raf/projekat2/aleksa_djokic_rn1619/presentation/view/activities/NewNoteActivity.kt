package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNewNoteBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityNoteBinding

class NewNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewNoteBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners();
    }

    private fun initListeners() {
        binding.saveBtn.setOnClickListener {

        }

        binding.cancelBtn.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}