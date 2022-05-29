package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat2.aleksa_djokic_rn1619.R
import rs.raf.projekat2.aleksa_djokic_rn1619.databinding.ActivityLoginBinding
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.composable.LoginScreen

class LoginActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityLoginBinding
    private val PIN = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        // sada kada smo ubacili setContent mozemo da obrisemo setContentView
        // ako se nalazimo u fragmentu onda ne mozemo da kazemo ovo setContent
        setContent {
            LoginScreen() {
                isInputValid(it)
            }
        }
    }

    private fun isInputValid(value : String) {
        if (value == "OK") {
            val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putBoolean("LOGGED_IN", true)
                apply()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
    }
}