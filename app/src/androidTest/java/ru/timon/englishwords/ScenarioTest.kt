package ru.timon.englishwords

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import ru.timon.englishwords.main.practicePage

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule

    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var practicePage: practicePage

    @Test
    fun testCase_1() {
        practicePage = practicePage(word = "sky", translation = "небо")
        practicePage.assertInitialState()

        practicePage.type("небо")
        practicePage.check()
        practicePage.assertCorrectState()

        practicePage.next()
        practicePage = practicePage(word = "tree", translation = "дерево")
        practicePage.assertInitialState()
    }

    @Test
    fun testCase_2() {
        practicePage = practicePage(word = "sky", translation = "небо")
        practicePage.assertInitialState()

        practicePage.type("небо")
        practicePage.check()
        practicePage.assertCorrectState()

        practicePage.next()
        practicePage = practicePage(word = "tree", translation = "дерево")
        practicePage.assertInitialState()

        practicePage.type("три")
        practicePage.check()
        practicePage.assertIncorrectState()

        practicePage.type("дерево")
        practicePage.check()
        practicePage.assertCorrectState()

        practicePage.next()
        practicePage = practicePage(word = "blue", translation = "синий")
        practicePage.assertInitialState()

        practicePage.type("дерево")
        practicePage.check()
        practicePage.assertIncorrectState()

        practicePage.next()
        practicePage = practicePage(word = "car", translation = "машина")
        practicePage.assertInitialState()

        practicePage.type("дерево")
        practicePage.check()
        practicePage.assertIncorrectState()

        practicePage.show()
        practicePage.assertFailState()

        practicePage.next()
        practicePage = practicePage(word = "run", translation = "бежать")
        practicePage.assertInitialState()
    }
}