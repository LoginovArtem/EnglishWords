package ru.timon.englishwords

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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
            uiState.update(binding = binding)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as App).viewModel

        binding.checkButton.setOnClickListener {
            uiState = viewModel.check(text = binding.inputEditText.text.toString())
            uiState.update(binding = binding)
        }

        binding.showButton.setOnClickListener {
            uiState = viewModel.show()
            uiState.update(binding = binding)
        }

        binding.nextButton.setOnClickListener {
             uiState = viewModel.next()
            uiState.update(binding = binding)
        }

        binding.tryAgainButton.setOnClickListener {
            uiState = viewModel.tryAgain()
            uiState.update(binding = binding)
        }

        uiState = if (savedInstanceState == null) viewModel.init()
        else
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                savedInstanceState.getSerializable(KEY, PracticeUiState::class.java) as PracticeUiState
            else savedInstanceState.getSerializable(KEY) as PracticeUiState
        uiState.update(binding = binding)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onResume() {
        super.onResume()
        binding.inputEditText.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputEditText.removeTextChangedListener(textWatcher)
    }

    companion object {
        private const val KEY = "outStateKey"
    }
}