package ru.timon.englishwords.main

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher

class TextUi(
    id: Int,
    text: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {
    private val interaction: ViewInteraction = onView(
        allOf(
            withId(id),
            withText(text),
            containerIdMatcher,
            containerClassTypeMatcher,
            isAssignableFrom(TextView::class.java)

    ))

    fun assertTextVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertTextNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
