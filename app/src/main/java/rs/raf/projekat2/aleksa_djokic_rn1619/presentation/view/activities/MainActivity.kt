package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.view.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import rs.raf.projekat2.aleksa_djokic_rn1619.R
import rs.raf.projekat2.aleksa_djokic_rn1619.presentation.composable.testPractice

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        // sada kada smo ubacili setContent mozemo da obrisemo setContentView
        // ako se nalazimo u fragmentu onda ne mozemo da kazemo ovo setContent
        setContentView(R.layout.activity_main)
        setContent {
            Text(text = "Hello world")
        }

        // Fragment - obicno zelimo da ubacimo compose code u neki xml fajl, kucamo composeView
        setContentView(R.layout.activity_main)
        val composeHolder = findViewById<ComposeView>(R.id.composeHolder)
        composeHolder.setContent {
            testPractice()
        }
    }
}