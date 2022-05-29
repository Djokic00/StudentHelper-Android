package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val PIN = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        binding.loginBtn.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            val pin = binding.pinEt.text.toString()

            if (username.isBlank() || pin.isBlank()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pin == PIN) {
                val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putBoolean("LOGGED_IN", true)
                    apply()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "The PIN you have entered is incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}