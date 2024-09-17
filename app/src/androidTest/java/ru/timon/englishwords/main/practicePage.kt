package ru.timon.englishwords.main

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.timon.englishwords.R

class PracticePage(word: String, translation: String) {

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

    private val checkUi = CheckUi(
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

    private val tryAgainUi = ButtonUi(
        id = R.id.tryAgainButton,
        textResId = R.string.tryAgain,
        colorHex = "E7D1FF",
        containerIdMatcher,
        containerClassTypeMatcher
    )

    fun assertInitialState() {
        wordUi.assertTextVisible()
        translationUi.assertTextNotVisible()
        inputUi.assertInputVisibleEnabled()
        checkUi.isVisibleDisabled()
        showUi.notVisible()
        nextUi.notVisible()
        tryAgainUi.notVisible()
    }

    fun type(text: String) {
        inputUi.addInput(text)
    }

    fun deleteLetter() {
        inputUi.deleteLetter()
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

    fun tryAgain() {
        tryAgainUi.click()
    }

    fun assertCorrectState() {
        wordUi.assertTextVisible()
        translationUi.assertTextNotVisible()
        inputUi.assertInputVisibleDisabled()
        checkUi.notVisible()
        showUi.notVisible()
        nextUi.isVisible()
        tryAgainUi.notVisible()
    }

    fun assertIncorrectState() {
        wordUi.assertTextVisible()
        translationUi.assertTextNotVisible()
        inputUi.assertInputVisibleDisabledError()
        checkUi.notVisible()
        showUi.isVisible()
        nextUi.notVisible()
        tryAgainUi.isVisible()
    }

    fun assertFailState() {
        wordUi.assertTextVisible()
        translationUi.assertTextVisible()
        inputUi.assertInputNotVisible()
        checkUi.notVisible()
        showUi.notVisible()
        nextUi.isVisible()
        tryAgainUi.notVisible()
    }

    fun assertCheckableState() {
        wordUi.assertTextVisible()
        translationUi.assertTextNotVisible()
        inputUi.assertInputVisibleEnabled()
        checkUi.isVisibleEnabled()
        showUi.notVisible()
        nextUi.notVisible()
        tryAgainUi.notVisible()
    }
}