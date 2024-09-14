package ru.timon.englishwords

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import ru.timon.englishwords.main.MainPage

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule

    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var mainPage: MainPage

    @Test
    fun testCase_1() {
        mainPage = MainPage(word = "sky", translation = "небо")
        mainPage.assertInitialState()

        mainPage.type("небо")
        mainPage.check()
        mainPage.assertCorrectState()

        mainPage.next()
        mainPage = MainPage(word = "tree", translation = "дерево")
        mainPage.assertInitialState()
    }

    @Test
    fun testCase_2() {
        mainPage = MainPage(word = "sky", translation = "небо")
        mainPage.assertInitialState()

        mainPage.type("небо")
        mainPage.check()
        mainPage.assertCorrectState()

        mainPage.next()
        mainPage = MainPage(word = "tree", translation = "дерево")
        mainPage.assertInitialState()

        mainPage.type("три")
        mainPage.check()
        mainPage.assertIncorrectState()

        mainPage.type("дерево")
        mainPage.check()
        mainPage.assertCorrectState()

        mainPage.next()
        mainPage = MainPage(word = "blue", translation = "синий")
        mainPage.assertInitialState()

        mainPage.type("дерево")
        mainPage.check()
        mainPage.assertIncorrectState()

        mainPage.next()
        mainPage = MainPage(word = "car", translation = "машина")
        mainPage.assertInitialState()

        mainPage.type("дерево")
        mainPage.check()
        mainPage.assertIncorrectState()

        mainPage.show()
        mainPage.assertFailState()

        mainPage.next()
        mainPage = MainPage(word = "run", translation = "бежать")
        mainPage.assertInitialState()
    }
}