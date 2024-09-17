package ru.timon.englishwords

class PracticeViewModel(private val repository: PracticeRepository) {
    fun check(text: String): PracticeUiState {
        val word = repository.word()
        val translation = repository.translation()
        val isCorrect = translation.equals(text, true)
        return if (isCorrect) PracticeUiState.Correct(word, translation)
        else PracticeUiState.Incorrect(word, translation)
    }

    fun show(): PracticeUiState {
        val word = repository.word()
        val translation = repository.translation()
        return PracticeUiState.Fail(word, translation)
    }

    fun next(): PracticeUiState {
        repository.next()
        return init()
    }

    fun tryAgain(): PracticeUiState {
        return init()
    }

    fun handleUserInput(text: String): PracticeUiState {
        val word = repository.word()
        val translation = repository.translation()
        val isCheckable = text.isNotEmpty()
        return if (isCheckable) PracticeUiState.Checkable(word, translation)
        else PracticeUiState.Initial(word, translation)
    }

    fun init(): PracticeUiState {
        val word = repository.word()
        val translation = repository.translation()
        return  PracticeUiState.Initial(word, translation)
    }

}
