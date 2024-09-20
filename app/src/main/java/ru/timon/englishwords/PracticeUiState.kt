package ru.timon.englishwords

import android.view.View
import ru.timon.englishwords.databinding.ActivityMainBinding
import java.io.Serializable

interface PracticeUiState : Serializable {

    fun update(binding: ActivityMainBinding)

    abstract class Abstract(
        private val englishWord: String,
        private val translationWord: String,
        private val translationVisibility: Int,
        private val inputUiState: InputUiState,
        private val tryVisibility: Int,
        private val checkUiState: CheckUiState,
        private val nextVisibility: Int,
        private val showVisibility: Int
    ) : PracticeUiState {
        override fun update(binding: ActivityMainBinding) = with(binding) {
            wordTextView.text = englishWord
            translationTextView.text = translationWord
            translationTextView.visibility = translationVisibility
            inputUiState.update(inputLayout, inputEditText)
            tryAgainButton.visibility = tryVisibility
            checkUiState.update(checkButton)
            nextButton.visibility = nextVisibility
            showButton.visibility = showVisibility
        }
    }

    data class Initial(
        private val word: String,
        val translation: String,
        private val readUserInput: String = ""
    ) : Abstract(
        word,
        translation,
        View.INVISIBLE,
        InputUiState.Initial,
        View.INVISIBLE,
        CheckUiState.Initial,
        View.INVISIBLE,
        View.INVISIBLE
    )

    data class Checkable(val word: String, val translation: String) : Abstract(
        word,
        translation,
        View.INVISIBLE,
        InputUiState.Enabled,
        View.INVISIBLE,
        CheckUiState.Checkable,
        View.INVISIBLE,
        View.INVISIBLE
    )

    data class Correct(val word: String, val translation: String) : Abstract(
        word,
        translation,
        View.INVISIBLE,
        InputUiState.Disabled,
        View.INVISIBLE,
        CheckUiState.Invisible,
        View.VISIBLE,
        View.INVISIBLE
    )

    data class Incorrect(val word: String, val translation: String) : Abstract(
        word,
        translation,
        View.INVISIBLE,
        InputUiState.DisabledError,
        View.VISIBLE,
        CheckUiState.Invisible,
        View.INVISIBLE,
        View.VISIBLE
    )

    data class Fail(val word: String, val translation: String) : Abstract(
        word,
        translation,
        View.VISIBLE,
        InputUiState.Invisible,
        View.INVISIBLE,
        CheckUiState.Invisible,
        View.VISIBLE,
        View.INVISIBLE
    )
}

