package uk.co.massimocarli.espressotest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

  @get:Rule
  var activityRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun testRecyclerView_whenClickOnPosition0_messageIsShown() {
    onView(withId(R.id.recyclerView))
      .perform(RecyclerViewActions.actionOnItemAtPosition<DummyViewHolder>(0, click()))
    onView(ViewMatchers.withText("Selected 0")).inRoot(ToastMatcher())
      .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
  }

  @Test
  fun testRecyclerView_whenClickOnPosition30_messageIsShown() {
    onView(withId(R.id.recyclerView))
      .perform(RecyclerViewActions.actionOnItemAtPosition<DummyViewHolder>(30, click()))
    onView(ViewMatchers.withText("Selected 30")).inRoot(ToastMatcher())
      .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
  }

}