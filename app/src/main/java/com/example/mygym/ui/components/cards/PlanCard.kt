package com.example.mygym.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    onDeletePlan: (Int) -> Unit = {},
    plan: Plan
){

    var showDeletePlanDialog by remember { mutableStateOf(false) }

    if (showDeletePlanDialog) {
        AlertDialog(
            onDismissRequest = { showDeletePlanDialog = false },
            confirmButton = {
                OutlinedButton(
                    onClick = {
                        onDeletePlan(plan.planId)
                        showDeletePlanDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ){
                    Text(text = "Si, elimina")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDeletePlanDialog = false },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Annulla")
                }
            },
            text = {
                Text(text = "Sicuro di voler rimuovere la scheda '${plan.title}' ?")
            }
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth(),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = Color.Transparent,
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
            Spacer(modifier = Modifier.weight(1f))

            ElevatedButton(
                modifier = Modifier.height(100.dp),
                onClick = { showDeletePlanDialog = true },
                colors = ButtonDefaults.elevatedButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Red,
                ),
                shape = ShapeDefaults.ExtraSmall,
                ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_24),
                    contentDescription = null
                )
            }
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