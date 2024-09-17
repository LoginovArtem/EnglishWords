package ru.timon.englishwords

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

import ru.timon.englishwords.main.PracticePage


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule

    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    private lateinit var practicePage: PracticePage

    @Test
    fun testCase_1() {
        practicePage = PracticePage(word = "sky", translation = "небо")
        practicePage.assertInitialState()

        practicePage.type("небо")
        practicePage.assertCheckableState()

        practicePage.check()
        practicePage.assertCorrectState()

        practicePage.next()
        practicePage = PracticePage(word = "tree", translation = "дерево")
        practicePage.assertInitialState()
    }

    @Test
    fun testCase_2() {
        practicePage = PracticePage(word = "sky", translation = "небо")
        practicePage.assertInitialState()

        practicePage.type("н")
        practicePage.assertCheckableState()

        practicePage.deleteLetter()
        practicePage.assertInitialState()

        practicePage.type("небо")
        practicePage.assertCheckableState()

        practicePage.check()
        practicePage.assertCorrectState()

        practicePage.next()
        practicePage = PracticePage(word = "tree", translation = "дерево")
        practicePage.assertInitialState()

        practicePage.type("три")
        practicePage.assertCheckableState()

        practicePage.check()
        practicePage.assertIncorrectState()

        practicePage.tryAgain()
        practicePage.assertInitialState()

        practicePage.type("чтоэто")
        practicePage.assertCheckableState()

        practicePage.check()
        practicePage.assertIncorrectState()

        practicePage.show()
        practicePage.assertFailState()

        practicePage.next()
        practicePage = PracticePage(word = "run", translation = "бежать")
        practicePage.assertInitialState()
    }
}