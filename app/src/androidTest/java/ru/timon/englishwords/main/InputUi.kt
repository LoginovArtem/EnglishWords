package ru.timon.englishwords.main

import android.widget.FrameLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import ru.timon.englishwords.R
import ru.timon.englishwords.TextInputLayoutErrorEnabledMatcher

class InputUi {
    private val inputLayoutId: Int = R.id.inputLayout
    private val layoutInteraction: ViewInteraction = onView(
        allOf(
            withId(inputLayoutId),
            isAssignableFrom(TextInputLayout::class.java)
        )
    )

    private val inputInteraction: ViewInteraction = onView(
        allOf(
            withId(R.id.inputEditText),
            isAssignableFrom(TextInputEditText::class.java),
            withParent(isAssignableFrom(FrameLayout::class.java))
        )
    )

    fun assertInputVisibleEnabled() {
        layoutInteraction.check(matches(isDisplayed()))
            .check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(false)))
    }

    fun addInput(text: String) {
        inputInteraction.perform(typeText(text))
    }

    fun assertInputVisibleDisabled() {
        layoutInteraction.check(matches(isDisplayed()))
            .check(matches(not(isEnabled())))
            .check(matches(TextInputLayoutErrorEnabledMatcher(false)))
    }

    fun assertInputVisibleError() {
        layoutInteraction.check(matches(isDisplayed()))
            .check(matches(not(isEnabled())))
            .check(matches(TextInputLayoutErrorEnabledMatcher(true)))
    }

    fun assertInputNotVisible() {
        layoutInteraction.check(matches(not(isDisplayed())))
    }
}
