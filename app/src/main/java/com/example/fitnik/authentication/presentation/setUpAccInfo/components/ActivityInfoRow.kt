package com.example.fitnik.authentication.presentation.setUpAccInfo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitnik.ui.theme.secondary
import com.example.fitnik.ui.theme.secondary2
import com.example.fitnik.ui.theme.white

@Composable
fun ActivityInfoRow(
    modifier: Modifier = Modifier
) {
    val activityLevels = listOf(
        "Sedentary: little or no daily exercise",
        "Light activity: light exercise or sports 1–3 days a week",
        "Moderate activity: moderate exercise or sports 3–5 days a week",
        "Intense activity: intense exercise or sports 6–7 days a week",
        "Very intense activity: very intense exercise or physical work and daily training"
    )
    var objectives by remember { mutableIntStateOf(1) }
    var activityOptionSelection by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        DynamicSelectTextField(
            modifier = Modifier.weight(1f),
            options = activityLevels,
            label = "Activity",
            selectedValue = activityOptionSelection,
            displayIn2Sections = true
        ) { value ->
            activityOptionSelection = value
        }
        Spacer(modifier = Modifier.width(12.dp))
        Button(
            modifier = Modifier
                .width(140.dp)
                .defaultMinSize(minHeight = 56.dp)
                .padding(top = 4.dp)
                .background(Brush.horizontalGradient(
                    colors = listOf(secondary2, secondary)
                ),
                    shape = RoundedCornerShape(percent = 20)
                ),
            onClick = {
                if (objectives < 3) objectives++ else objectives = 1
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = white,
            ),
            contentPadding = PaddingValues(10.dp)
        ) {
            Text(changeObjectivesText(objectives), maxLines = 1)
        }
    }
}

fun changeObjectivesText(objective: Int): String {
    return when (objective) {
        1 -> "Gain Muscle"
        2 -> "Loose Fat"
        3 -> "Loose weight"
        else -> ""
    }
}