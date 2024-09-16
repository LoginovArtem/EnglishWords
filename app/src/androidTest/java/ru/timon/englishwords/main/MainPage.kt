package ru.timon.englishwords.main

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.timon.englishwords.R

class MainPage(word: String, translation: String) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val wordUi = TextUi(
        id = R.id.wordTextView,
        text = word,
        containerIdMatcher,
        containerClassTypeMatcher
    )

    private val translationUi = TextUi(
        id = R.id.translationTextView,
        text = translation,
        containerIdMatcher,
        containerClassTypeMatcher
    )

    private val inputUi = InputUi()

    private val checkUi = ButtonUi(
        id = R.id.checkButton,
        textResId = R.string.check,
        colorHex = "E7D1FF",
        containerIdMatcher,
        containerClassTypeMatcher
    )

    private val showUi = ButtonUi(
        id = R.id.showButton,
        textResId = R.string.show,
        colorHex = "E7D1FF",
        containerIdMatcher,
        containerClassTypeMatcher
    )

    private val nextUi = ButtonUi(
        id = R.id.nextButton,
        textResId = R.string.next,
        colorHex = "E7D1FF",
        containerIdMatcher,
        containerClassTypeMatcher
    )

    fun assertInitialState() {
        wordUi.assertTextVisible()
        translationUi.assertTextNotVisible()
        inputUi.assertInputVisibleEnabled()
        checkUi.isVisible()
        showUi.notVisible()
        nextUi.notVisible()
    }

    fun type(text: String) {
        inputUi.addInput(text)
    }

    fun check() {
        checkUi.click()
    }

    fun next() {
        nextUi.click()
    }

    fun show() {
        showUi.click()
    }

    fun assertCorrectState() {
        wordUi.assertTextVisible()
        translationUi.assertTextNotVisible()
        inputUi.assertInputVisibleDisabled()
        checkUi.notVisible()
        showUi.notVisible()
        nextUi.isVisible()
    }

    fun assertIncorrectState() {
        wordUi.assertTextVisible()
        translationUi.assertTextNotVisible()
        inputUi.assertInputVisibleError()
        checkUi.isVisible()
        showUi.isVisible()
        nextUi.isVisible()
    }

    fun assertFailState() {
        wordUi.assertTextVisible()
        translationUi.assertTextVisible()
        inputUi.assertInputNotVisible()
        checkUi.notVisible()
        showUi.notVisible()
        nextUi.isVisible()
    }


}