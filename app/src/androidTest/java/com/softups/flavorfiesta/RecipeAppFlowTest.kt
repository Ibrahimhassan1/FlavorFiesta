package com.softups.flavorfiesta

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.di.AppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class RecipeAppFlowTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun navigateToRecipeDetailAndBack_whenFirstRecipeItemClicked() {
        val testRecipe = TestUtils.singleDummyRecipe
        composeTestRule.onNodeWithText(testRecipe.name).isDisplayed()
        composeTestRule.onNodeWithText(testRecipe.name).performClick()
        composeTestRule.onNodeWithText(testRecipe.ingredients[0]).isDisplayed()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back_button))
            .performClick()
        composeTestRule.onNodeWithText(testRecipe.name).isDisplayed()
    }
}