package ru.timon.englishwords

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.timon.englishwords.main.PracticePage


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule

    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    private lateinit var practicePage: PracticePage

    private fun ActivityScenarioRule<*>.doWithRecreate(block: ()->Unit) {
        block.invoke()
        scenario.recreate()
        block.invoke()
    }

    @Test
    fun testCase_1() {
        practicePage = PracticePage(word = "sky", translation = "небо")
        activityRule.doWithRecreate(practicePage::assertInitialState)

        practicePage.type("небо")
        activityRule.doWithRecreate(practicePage::assertCheckableState)

        practicePage.check()
        activityRule.doWithRecreate(practicePage::assertCorrectState)

        practicePage.next()
        practicePage = PracticePage(word = "tree", translation = "дерево")
        activityRule.doWithRecreate(practicePage::assertInitialState)
    }

    @Test
    fun testCase_2() {
        practicePage = PracticePage(word = "sky", translation = "небо")
        activityRule.doWithRecreate(practicePage::assertInitialState)

        practicePage.type("н")
        activityRule.doWithRecreate(practicePage::assertCheckableState)

        practicePage.clearText()
        activityRule.doWithRecreate(practicePage::assertNoTextState)

        practicePage.type("небо")
        activityRule.doWithRecreate(practicePage::assertCheckableState)

        practicePage.check()
        activityRule.doWithRecreate(practicePage::assertCorrectState)

        practicePage.next()
        practicePage = PracticePage(word = "tree", translation = "дерево")
        activityRule.doWithRecreate(practicePage::assertInitialState)

        practicePage.type("три")
        activityRule.doWithRecreate(practicePage::assertCheckableState)

        practicePage.check()
        activityRule.doWithRecreate(practicePage::assertIncorrectState)

        practicePage.tryAgain()
        activityRule.doWithRecreate(practicePage::assertNoTextState)

        practicePage.type("чтоэто")
        activityRule.doWithRecreate(practicePage::assertCheckableState)

        practicePage.check()
        activityRule.doWithRecreate(practicePage::assertIncorrectState)

        practicePage.show()
        activityRule.doWithRecreate(practicePage::assertFailState)

        practicePage.next()
        practicePage = PracticePage(word = "run", translation = "бежать")
        activityRule.doWithRecreate(practicePage::assertInitialState)
    }
}