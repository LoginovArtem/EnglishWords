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
import ru.timon.englishwords.ButtonColorMatcher

class ButtonUi(
    id: Int,
    textResId: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractButtonUi(onView(
        allOf(
            withId(id),
            withText(textResId),
            containerIdMatcher,
            containerClassTypeMatcher
        )
    )
)

abstract class AbstractButtonUi(protected val interaction: ViewInteraction) {

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }

    fun isVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun notVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
