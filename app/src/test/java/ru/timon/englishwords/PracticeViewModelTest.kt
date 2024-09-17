package ru.timon.englishwords

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class PracticeViewModelTest {

    private lateinit var viewModel: PracticeViewModel

    @Before
    fun setup() {
        viewModel = PracticeViewModel()
    }

    @Test
    fun test_1() {
        var actual: PracticeUiState = viewModel.init()
        var expected: PracticeUiState =
            PracticeUiState.Initial(word = "sky", translation = "небо")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "небо")
        expected = PracticeUiState.Checkable(word = "sky", translation = "небо")
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = PracticeUiState.Correct(word = "sky", translation = "небо")
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = PracticeUiState.Initial(word = "tree", translation = "дерево")
        assertEquals(expected, actual)
    }

    @Test
    fun test_2() {
        var actual: PracticeUiState = viewModel.init()
        var expected: PracticeUiState =
            PracticeUiState.Initial(word = "sky", translation = "небо")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "н")
        expected = PracticeUiState.Checkable(word = "sky", translation = "небо")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "")
        expected = PracticeUiState.Initial(word = "sky", translation = "небо")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "небо")
        expected = PracticeUiState.Checkable(word = "sky", translation = "небо")
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = PracticeUiState.Correct(word = "sky", translation = "небо")
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = PracticeUiState.Initial(word = "tree", translation = "дерево")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput("три")
        expected = PracticeUiState.Checkable(word = "tree", translation = "дерево")
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = PracticeUiState.Incorrect(word = "tree", translation = "дерево")
        assertEquals(expected, actual)

        actual = viewModel.tryAgain()
        expected = PracticeUiState.Initial(word = "tree", translation = "дерево")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput("три")
        expected = PracticeUiState.Checkable(word = "tree", translation = "дерево")
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = PracticeUiState.Incorrect(word = "tree", translation = "дерево")
        assertEquals(expected, actual)

        actual = viewModel.show()
        expected = PracticeUiState.Fail(word = "tree", translation = "дерево")
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = PracticeUiState.Initial(word = "run", translation = "бежать")
        assertEquals(expected, actual)
    }
}