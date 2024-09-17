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

class FakeRepository : PracticeRepository {
    private val enList = listOf("sky", "tree", "run")
    private val ruList = listOf("небо", "дерево", "бежать")
    private var index = 0

    override fun word(): String = enList[index]

    override fun translation(): String = ruList[index]

    override fun next() {
        index++
        if (index == enList.size)
            index = 0
    }
}

