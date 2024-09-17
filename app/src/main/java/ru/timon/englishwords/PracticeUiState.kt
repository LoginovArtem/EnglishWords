package ru.timon.englishwords

import ru.timon.englishwords.databinding.ActivityMainBinding

interface PracticeUiState {

    fun update(binding: ActivityMainBinding)  : Unit = Unit

    data class  Initial(val word: String, val translation: String) : PracticeUiState
    data class Checkable(val word: String, val translation: String) : PracticeUiState
    data class Correct(val word: String, val translation: String) : PracticeUiState
    data class Incorrect(val word: String, val translation: String) : PracticeUiState
    data class Fail(val word: String, val translation: String) : PracticeUiState
}
