package ru.timon.englishwords

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import ru.timon.englishwords.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel : PracticeViewModel = PracticeViewModel(object : PracticeRepository {
            override fun word(): String {
                TODO("Not yet implemented")
            }

            override fun translation(): String {
                TODO("Not yet implemented")
            }

            override fun next() {
                TODO("Not yet implemented")
            }

        })

        binding.checkButton.setOnClickListener {
            val uiState : PracticeUiState = viewModel.check(text = binding.inputEditText.text.toString())
            uiState.update(binding = binding)
        }

        binding.showButton.setOnClickListener {
            val uiState : PracticeUiState = viewModel.show()
            uiState.update(binding = binding)
        }

        binding.nextButton.setOnClickListener {
            val uiState : PracticeUiState = viewModel.next()
            uiState.update(binding = binding)
        }

        binding.tryAgainButton.setOnClickListener {
            val uiState : PracticeUiState = viewModel.tryAgain()
            uiState.update(binding = binding)
        }

        binding.inputEditText.addTextChangedListener {
            val uiState : PracticeUiState = viewModel.handleUserInput(text = it.toString())
            uiState.update(binding = binding)
        }

        val uiState : PracticeUiState = viewModel.init()
        uiState.update(binding = binding)
    }
}