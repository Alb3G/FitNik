package com.example.fitnik.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnik.R
import com.example.fitnik.ui.theme.lightGray

@Composable
fun AuthIconButton(
    modifier: Modifier = Modifier,
    iconResId: Int,
    contentDescription: String
) {
    IconButton(
        onClick = { /* Acción al hacer clic */ },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = contentDescription,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }

}


@Preview(
    showBackground = true
)
@Composable
fun AuthIconButtonPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(width = 1.dp, color = lightGray, shape = RoundedCornerShape(12.dp))
                .background(Color.Transparent)
                .width(50.dp)
                .height(50.dp)
        ) {
            Icon(painterResource(R.drawable.icons8_google), "Sign Up with google")
        }
    }
}