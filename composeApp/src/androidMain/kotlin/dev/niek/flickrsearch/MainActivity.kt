package dev.niek.flickrsearch

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dev.niek.flickrsearch.data.repositories.DefaultGreetingRepository
import dev.niek.flickrsearch.domain.usecases.GetGreetingUseCase
import dev.niek.flickrsearch.presentation.MainScreen

class MainActivity : AppCompatActivity() {

    // TODO: Missing ViewModel and DI framework
    private val getGreetingUseCase = GetGreetingUseCase(
        greetingRepo = DefaultGreetingRepository(),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(
                greeting = getGreetingUseCase(),
            )
        }
    }
}
