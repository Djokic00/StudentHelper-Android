package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


//@Composable
//fun testPractice() {
//    Text(
//        // modifier = Modifier.background()
//        text = "Hello from fragment", fontSize = 10.sp)
//}
//
//@Preview
//@Composable
//fun testPracticePreview() {
//    testPractice()
//}

@Composable
fun testPractice() {
    Row (
        modifier = Modifier
            .fillMaxSize(),
//        horizontalArrangement = Arrangement.spacedBy(10.dp)
//        horizontalArrangement = Arrangement.SpaceAround
    ) {
    Text(
        text = "TopLeft"
    )

    Text(
        text = "TopRight"
    )}
}

@Preview
@Composable
fun testPracticePreview() {
    testPractice()
}

