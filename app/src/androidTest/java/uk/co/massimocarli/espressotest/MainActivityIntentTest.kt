package uk.co.massimocarli.espressotest

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Rule
import org.junit.Test


class MainActivityIntentTest {

  @get:Rule
  val intentsTestRule = IntentsTestRule(MainActivity::class.java)

  @Test
  fun testRecyclerView_whenClickOnPosition3_intentHasLaunched() {
    Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
      .perform(RecyclerViewActions.actionOnItemAtPosition<DummyViewHolder>(3, ViewActions.click()))
    intended(IntentMatchers.hasAction(Intent.ACTION_MAIN))
    intended(IntentMatchers.hasCategories(setOf(Intent.CATEGORY_LAUNCHER)))
    intended(IntentMatchers.hasExtra("NAME", "VALUE"))
  }

  @Test
  fun testRecyclerView_whenClickOnPosition5_activityReturnsAValue() {
    // We create a mock of the result from the destination Activity
    val resultData = Intent().apply {
      putExtra("result", 37)
    }
    val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
    // We create the actual mock for the intent
    intending(hasAction("MyAction")).respondWith(result)
    // We launch the
    Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
      .perform(RecyclerViewActions.actionOnItemAtPosition<DummyViewHolder>(5, ViewActions.click()))
    Espresso.onView(ViewMatchers.withText("Result: 37")).inRoot(ToastMatcher())
      .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
  }
}