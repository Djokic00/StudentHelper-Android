package rs.raf.projekat2.aleksa_djokic_rn1619.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.raf.projekat2.aleksa_djokic_rn1619.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(onClick : (String) -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
        .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
            ) {
        var username = remember { mutableStateOf ("") }
        var password = remember { mutableStateOf("") }
        val showPassword = remember { mutableStateOf(false) }

        Text(
            text = stringResource(R.string.login),
            fontSize = 30.sp,
            color = RafBlue
        )

        Image(
            painterResource(R.drawable.login_icon),
            contentDescription = null,
            modifier = Modifier.requiredSize(170.dp)
        )

        TextField(
            value = username.value,
            onValueChange = {username.value = it},
            label = { Text(text = stringResource(R.string.username))},
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
        )

        TextField(
            value = password.value,
            onValueChange = {password.value = it},
            label = { Text(text = stringResource(R.string.pin))},
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            trailingIcon = {
                if (showPassword.value) {
                    IconButton(onClick = { showPassword.value = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = stringResource(R.string.hide_password)
                        )
                    }
                } else {
                    IconButton(onClick = { showPassword.value = true}) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = stringResource(R.string.show_password)
                        )
                    }
                }
            }
        )
        Button(onClick = {
            if (username.value.isBlank() || username.value.length < 3) {
                Toast.makeText(context, "Username must contain at least 3 characters!", Toast.LENGTH_SHORT).show()
            }
            else if (password.value != "1234") {
                Toast.makeText(context, "Invalid pin!", Toast.LENGTH_SHORT).show()
            } else onClick("OK")
        },
            modifier = Modifier.size(150.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = RafBlue,
                contentColor = Color.White
            )
        ) {
            Text (
                text = stringResource(R.string.login)
            )
        }
    }
}
