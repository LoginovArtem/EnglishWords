package ru.timon.englishwords

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class PracticeViewModelTest {

    private lateinit var viewModel: PracticeViewModel

    @Before
    fun setup() {
        viewModel = PracticeViewModel(repository = FakeRepository())
    }

    @Test
    fun test_1() {
        var actual: PracticeUiState = viewModel.init()
        var expected: PracticeUiState =
            PracticeUiState.Initial(word = "sky", translation = "небо")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "небо")
        expected = PracticeUiState.Checkable
        assertEquals(expected, actual)

        actual = viewModel.check(text = "небо")
        expected = PracticeUiState.Correct
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
        expected = PracticeUiState.Checkable
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "")
        expected = PracticeUiState.NoText
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "небо")
        expected = PracticeUiState.Checkable
        assertEquals(expected, actual)

        actual = viewModel.check(text = "небо")
        expected = PracticeUiState.Correct
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = PracticeUiState.Initial(word = "tree", translation = "дерево")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput("три")
        expected = PracticeUiState.Checkable
        assertEquals(expected, actual)

        actual = viewModel.check(text = "три")
        expected = PracticeUiState.Incorrect
        assertEquals(expected, actual)

        actual = viewModel.tryAgain()
        expected = PracticeUiState.NoText
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput("три")
        expected = PracticeUiState.Checkable
        assertEquals(expected, actual)

        actual = viewModel.check(text = "три")
        expected = PracticeUiState.Incorrect
        assertEquals(expected, actual)

        actual = viewModel.show()
        expected = PracticeUiState.Fail
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = PracticeUiState.Initial(word = "run", translation = "бежать")
        assertEquals(expected, actual)
    }
}

class FakeRepository : PracticeRepository {
    private val list = listOf("sky" to "небо", "tree" to "дерево", "run" to "бежать")
    private var index = 0

    override fun word(): String = list[index].first
    override fun translation(): String = list[index].second

    override fun next() {
        index++
        if (index == list.size ) {
            index = 0
        }
    }

}

