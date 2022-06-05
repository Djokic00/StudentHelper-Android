//package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.composable
//
//
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.drawscope.rotate
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import rs.raf.projekat2.aleksa_djokic_rn1619.R
//import timber.log.Timber
//
//@Composable
//fun makeChart(noteHistoryList: List<Int>) {
//    Box(
//        modifier = Modifier.padding(30.dp, 50.dp)
//    ) {
//        Text(
//            text = stringResource(R.string.statistics),
//            fontSize = 30.sp,
//            color = RafBlue,
//            textAlign = TextAlign.Center,
//        )
//    }
//
//    Column {
//        for (i in 0..noteHistoryList.size) {
//            val numberOfNotes = noteHistoryList[i]
//            Canvas(modifier = Modifier.fillMaxSize()) {
//            rotate(degrees = 180F) {
//                drawRect( // Prvo crtamo za 5ti dan (tj. danasnji dan)
//                    color = RafBlue,
//                    size = Size(width = 120f, height = numberOfNotes * 50f),
//                    topLeft = Offset(x = 225f, y = 700f)
//                )
//            }
//        }
////                drawRect( // Prvo crtamo za 5ti dan (tj. danasnji dan)
////                    color = RafBlue,
////                    size = Size(width = 120f, height = numberOfNotes * 50f),
////                    topLeft = Offset(x = 225f, y = 700f)
////                )
////            }
//
////            drawRect(
////                color = RafBlue,
////                size = Size(width = 120f, height = 390f),
////                topLeft = Offset(x = 475f, y = 700f)
////            )
////            drawRect(
////                color = RafBlue,
////                size = Size(width = 120f, height = 500f),
////                topLeft = Offset(x = 600f, y = 700f)
////            )
////            drawRect( // Poslednje crtamo za prvi dan (tj pre 5 dana)
////                color = RafBlue,
////                size = Size(width = 120f, height = 420f),
////                topLeft = Offset(x = 725f, y = 700f)
////            )
//    }
//}
//
//
//
