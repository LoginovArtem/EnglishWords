package ru.timon.englishwords.main

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import ru.timon.englishwords.R

class CheckUi(containerIdMatcher: Matcher<View>, containerClassTypeMatcher: Matcher<View>) :
    AbstractButtonUi(
        onView(
            allOf(
                containerIdMatcher,
                containerClassTypeMatcher,
                withId(R.id.checkButton),
                withText(R.string.check)
            )
        )
    ) {
        fun isVisibleEnabled() {
            interaction.check(matches(isDisplayed())).check(matches(isEnabled()))
        }

        fun isVisibleDisabled() {
            interaction.check(matches(isDisplayed())).check(matches(not(isEnabled())))
        }
}