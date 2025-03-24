package com.example.fitnik.authentication.presentation.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.authentication.presentation.signup.SignUpViewModel

@Composable
fun PasswordRequirements(
    password: String,
    viewModel: SignUpViewModel
) {
    val result = viewModel.validatePassword(password)

    val requirements = listOf(
        "Minimum 8 characters" to result.metMinLength,
        "1 Mayus" to result.metUpperCase,
        "1 number" to result.metNumber,
        "1 Special character" to result.metSpecialChar
    )

    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        requirements.forEach { (text, met) ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = if (met) painterResource(R.drawable.check) else painterResource(R.drawable.error),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(end = 4.dp),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}