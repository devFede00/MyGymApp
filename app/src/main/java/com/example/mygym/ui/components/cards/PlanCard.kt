package com.example.mygym.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mygym.R
import com.example.mygym.database.Plan
import com.example.mygym.ui.theme.MyGymTheme

@Composable
fun PlanCard(
    modifier: Modifier = Modifier,
    onPlanClick:(Int)->Unit,
    plan: Plan
){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        border = BorderStroke(2.dp, Color.Gray),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = {onPlanClick(plan.planId)}
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), // Assicura che il Row occupi tutta la larghezza disponibile
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = null,
                Modifier.size(100.dp)
            )
            Text(
                text = plan.title,
                modifier = Modifier.padding(start = 45.dp),
                fontWeight = FontWeight.Bold
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanCardPreview(){
    MyGymTheme {
        PlanCard(onPlanClick = {}, plan = Plan(planId = 0, title = "planPreview"))
    }
}