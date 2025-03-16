package com.example.fitnik.authentication.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnik.R
import com.example.fitnik.authentication.presentation.signup.components.InputGroup
import com.example.fitnik.core.presentation.AuthIconButton
import com.example.fitnik.core.presentation.LoginDivider
import com.example.fitnik.core.presentation.AuthTextField
import com.example.fitnik.core.presentation.FitnikDefButton
import com.example.fitnik.ui.theme.lightGray
import com.example.fitnik.ui.theme.primary
import com.example.fitnik.ui.theme.secondary
import com.example.fitnik.ui.theme.white

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(white)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hey there,", fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(6.dp))
        Text("Create an Account", fontSize = 24.sp, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))
        InputGroup()
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = !isChecked },
                colors = CheckboxDefaults.colors(
                    checkedColor = primary,
                    uncheckedColor = lightGray,
                    checkmarkColor = white
                ),
            )
            Text(
                text = "By continuing you accept our Privacy Policy and Terms of Use",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(110.dp))
        FitnikDefButton(
            text = "Sign Up",
            modifier = Modifier
            .defaultMinSize(minHeight = 84.dp)
            .fillMaxWidth()
            .padding(bottom = 24.dp),
            textStyle = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            )
        ) {  }
        LoginDivider()
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AuthIconButton(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(width = 1.dp, color = lightGray, shape = RoundedCornerShape(12.dp))
                    .background(Color.Transparent),
                iconResId = R.drawable.icons8_google,
                contentDescription = "Sign Up with google"
            )
            Spacer(modifier = Modifier.width(24.dp))
            AuthIconButton(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(width = 1.dp, color = lightGray, shape = RoundedCornerShape(12.dp))
                    .background(Color.Transparent),
                iconResId = R.drawable.icons8_github,
                contentDescription = "Sign Up with Github"
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Already have an account?", style = MaterialTheme.typography.bodySmall)
            TextButton(
                onClick = onLoginClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = secondary
                )
            ) {
                Text("Login")
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    Column(
        modifier = Modifier
            .background(white)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hey there,", fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Create an Account", fontSize = 24.sp, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))
        AuthTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            tfValue = "",
            label = "First Name",
            leadingIcon = R.drawable.profile,
            contentDescription = "Profile icon",
        )
    }
}