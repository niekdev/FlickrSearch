package dev.niek.flickrsearch

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dev.niek.flickrsearch.domain.usecases.GetGreetingUseCase
import dev.niek.flickrsearch.presentation.main.MainScreen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    // TODO: Missing ViewModel
    private val getGreetingUseCase: GetGreetingUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(
                greeting = getGreetingUseCase(),
            )
        }
    }
}
