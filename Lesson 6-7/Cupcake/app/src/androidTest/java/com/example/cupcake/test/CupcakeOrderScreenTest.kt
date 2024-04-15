package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test
/*
Pruebas unitarias para la ventan de ordenes de mi app de Cupcake
 */
class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderUiState = OrderUiState(
        quantity = 6,
        flavor = "Vanilla",
        date = "Wed Jul 21",
        price = "$100",
        pickupOptions = listOf()
    )

    @Test
    fun startOrderScreen_verifyContent() {

        // When StartOrderScreen is loaded
        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = {}
            )
        }

        // Then all the options are displayed on the screen.
        DataSource.quantityOptions.forEach {
            composeTestRule.onNodeWithStringId(it.first).assertIsDisplayed()
        }
    }


    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And subtotal
        val subtotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        // Then all the options are displayed on the screen.
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        // And then the subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()

        // And then the next button is disabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

   
    @Test
    fun selectOptionScreen_optionSelected_NextButtonEnabled() {
        // Given list of options
        val flavours = listOf("Vainilla", "Chocolate", "Fresa", "Cookie&Cream", "Mango")
        // And sub total
        val subTotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subTotal, options = flavours)
        }

        // And one option is selected
        composeTestRule.onNodeWithText("Vainilla").performClick()

        // Then the next button is disabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }


    @Test
    fun summaryScreen_verifyContentDisplay() {
        // When Summary Screen is loaded
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = fakeOrderUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> },
            )
        }

        // Then the UI is updated correctly.
        composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                fakeOrderUiState.price
            )
        ).assertIsDisplayed()
    }
}
