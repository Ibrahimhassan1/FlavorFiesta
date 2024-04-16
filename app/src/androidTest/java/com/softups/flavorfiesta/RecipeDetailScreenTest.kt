package com.softups.flavorfiesta

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performScrollToNode
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.ui.recipe_detail.RecipeDetailsState
import com.softups.flavorfiesta.ui.recipe_detail.components.RecipeDetailScreenPortrait
import org.junit.Rule
import org.junit.Test

class RecipeDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun displayRecipeDetailHeader_whenScreenIsLoaded() {
        val testRecipe = TestUtils.singleDummyRecipe
        composeTestRule.setContent {
            RecipeDetailScreenPortrait(
                recipeDetailsState = RecipeDetailsState(selectedRecipe = testRecipe)
            )
        }
        composeTestRule.onNodeWithText(testRecipe.name).assertIsDisplayed()
        composeTestRule.onNodeWithTag(testRecipe.image).assertExists()
    }

    @Test
    fun displayRecipeDetailFacts_whenScreenIsLoaded() {
        val testRecipe = TestUtils.singleDummyRecipe
        composeTestRule.setContent {
            RecipeDetailScreenPortrait(
                recipeDetailsState = RecipeDetailsState(selectedRecipe = testRecipe)
            )
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.prep_time))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.time_in_minutes, testRecipe.prepTimeMinutes
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.cook_time))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.time_in_minutes, testRecipe.cookTimeMinutes
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.servings))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(testRecipe.servings.toString()).assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.calories_per_servings))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(testRecipe.caloriesPerServing.toString()).assertIsDisplayed()
    }

    @Test
    fun displayRecipeDetailIngredients_whenScreenIsLoaded() {
        val testRecipe = TestUtils.singleDummyRecipe
        composeTestRule.setContent {
            RecipeDetailScreenPortrait(
                recipeDetailsState = RecipeDetailsState(selectedRecipe = testRecipe)
            )
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.ingredients))
            .assertIsDisplayed()

        testRecipe.ingredients.forEach {
            composeTestRule.onNodeWithText(
                composeTestRule.activity.getString(
                    R.string.ingredient_with_bullet_point, it
                ), useUnmergedTree = true
            ).assertExists()
        }
    }

    @Test
    fun displayRecipeDetailInstructions_whenScreenIsLoaded() {
        val testRecipe = TestUtils.singleDummyRecipe
        composeTestRule.setContent {
            RecipeDetailScreenPortrait(
                recipeDetailsState = RecipeDetailsState(selectedRecipe = testRecipe)
            )
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.instructions))
            .assertIsDisplayed()

        testRecipe.instructions.forEachIndexed { index, instruction ->
            composeTestRule.onRoot().onChild()
                .performScrollToNode(
                    hasText(
                        composeTestRule.activity.getString(
                            R.string.step_number, "${index + 1}"
                        )
                    )
                )
                .assertIsDisplayed()
            composeTestRule.onNodeWithText(instruction).isDisplayed()
        }
    }

}