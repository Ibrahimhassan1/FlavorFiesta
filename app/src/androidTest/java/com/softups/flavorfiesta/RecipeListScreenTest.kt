package com.softups.flavorfiesta

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.domain.model.Recipe
import com.softups.flavorfiesta.ui.recipe_list.RecipeListScreen
import com.softups.flavorfiesta.ui.recipe_list.RecipeListState
import org.junit.Rule
import org.junit.Test

class RecipeListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun displayLoadingSpinner_whenScreenIsLoading() {
        val testRecipeListState = RecipeListState(
            isLoading = true,
            recipes = emptyList(),
            error = ""
        )
        composeTestRule.setContent {
            RecipeListScreen(
                recipeListState = testRecipeListState,
                onItemClick = {},
                onRefreshClick = {})
        }
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.loading_recipes_content_description))
            .isDisplayed()
    }

    @Test
    fun displayErrorMessage_whenScreenIsInErrorState() {
        val testRecipeListState = RecipeListState(
            error = "Test Error"
        )
        composeTestRule.setContent {
            RecipeListScreen(
                recipeListState = testRecipeListState,
                onItemClick = {},
                onRefreshClick = {})
        }
        composeTestRule.onNodeWithText("Test Error").assertExists()
    }

    @Test
    fun displayEmptyMessageComponent_whenDataIsEmpty() {
        val testRecipeListState = RecipeListState(
            recipes = listOf()
        )
        composeTestRule.setContent {
            RecipeListScreen(
                recipeListState = testRecipeListState,
                onItemClick = {},
                onRefreshClick = {})
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.empty_list))
            .assertExists()
    }

    @Test
    fun displayRecipeList_whenDataIsLoaded() {
        val testRecipesList = TestUtils.dummyRecipesDto.toRecipes()

        val testRecipeListState = RecipeListState(
            recipes = testRecipesList
        )
        composeTestRule.setContent {
            RecipeListScreen(
                recipeListState = testRecipeListState,
                onItemClick = {},
                onRefreshClick = {})
        }
        composeTestRule.onNodeWithText(testRecipesList[0].name).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.time_in_minutes, testRecipesList[0].prepTimeMinutes
            )
        ).assertExists()

        composeTestRule.onNodeWithText(testRecipesList[1].name).assertExists()

        composeTestRule.onNodeWithTag(
            "${testRecipesList[1].id} ${
                composeTestRule.activity.getString(
                    R.string.preparation_time_in_minutes_test_tag
                )
            }", useUnmergedTree = true
        ).assertExists()

        // verify that image is displayed
        composeTestRule.onNodeWithTag(
            testRecipesList[0].image, useUnmergedTree = true
        ).assertExists()

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")
    }

    @Test
    fun navigateToDetailScreen_whenRecipeItemClicked() {
        var onClickAction = false
        var onClickedRecipe: Recipe? = null
        val testRecipesList = TestUtils.dummyRecipesDto.toRecipes()

        val testRecipeListState = RecipeListState(
            recipes = testRecipesList
        )
        composeTestRule.setContent {
            RecipeListScreen(
                recipeListState = testRecipeListState,
                onItemClick = {
                    onClickedRecipe = it
                    onClickAction = true
                },
                onRefreshClick = {})
        }
        composeTestRule.onNodeWithText(testRecipesList[0].name).performClick()

        assert(onClickAction)
        assert(onClickedRecipe?.name == testRecipesList[0].name)
    }

}