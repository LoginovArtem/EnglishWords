package ru.timon.englishwords

class PracticeViewModel(private val repository: PracticeRepository) {
    fun check(text: String): PracticeUiState {
        val translation = repository.translation()
        val isCorrect = translation.equals(text, true)
        return if (isCorrect) PracticeUiState.Correct
        else PracticeUiState.Incorrect
    }

    fun show(): PracticeUiState {
        return PracticeUiState.Fail
    }

    fun next(): PracticeUiState {
        repository.next()
        return init()
    }

    fun tryAgain(): PracticeUiState {
        return PracticeUiState.NoText
    }

    fun handleUserInput(text: String): PracticeUiState {
        val isCheckable = text.isNotEmpty()
        return if (isCheckable) PracticeUiState.Checkable
        else PracticeUiState.NoText
    }

    fun init(isFirstRun: Boolean = true): PracticeUiState {
        if (isFirstRun) {
            val word = repository.word()
            val translation = repository.translation()
            return PracticeUiState.Initial(word, translation)
        } else {
            return PracticeUiState.Empty
        }
    }

}
