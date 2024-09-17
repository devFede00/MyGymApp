package com.example.mygym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.mygym.database.GymDatabase
import com.example.mygym.database.MIGRATION_1_2
import com.example.mygym.ui.screens.MyApp
import com.example.mygym.ui.theme.MyGymTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            GymDatabase::class.java,
            "gym_database.db"
        ).addMigrations(MIGRATION_1_2)
            .build()
    }

    private val viewModel by viewModels<GymManagerViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return GymManagerViewModel(db.gymDao()) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Questa riga permette al layout di adattarsi agli insets di sistema
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            MyGymTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // Usa il background del tema
                ) {
                    MyApp(viewModel = viewModel)
                }
            }
        }
    }
}
