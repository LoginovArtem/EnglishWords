package ru.timon.englishwords

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.timon.englishwords.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var uiState: PracticeUiState
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PracticeViewModel
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)  = Unit
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)  = Unit
        override fun afterTextChanged(p0: Editable?) {
            uiState = viewModel.handleUserInput(text = p0.toString())
            update.invoke()
        }
    }

    private val update: ()->Unit = {
        uiState.update(
            binding.wordTextView,
            binding.translationTextView,
            binding.inputView,
            binding.checkButton,
            binding.tryAgainButton,
            binding.nextButton,
            binding.showButton
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as App).viewModel

        binding.checkButton.setOnClickListener {
            uiState = viewModel.check(text = binding.inputView.text())
            update.invoke()
        }

        binding.showButton.setOnClickListener {
            uiState = viewModel.show()
            update.invoke()
        }

        binding.nextButton.setOnClickListener {
             uiState = viewModel.next()
            update.invoke()
        }

        binding.tryAgainButton.setOnClickListener {
            uiState = viewModel.tryAgain()
            update.invoke()
        }

        uiState = viewModel.init(savedInstanceState==null)

        update.invoke()
    }

    override fun onResume() {
        super.onResume()
        binding.inputView.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputView.removeTextChangedListener(textWatcher)
    }

}