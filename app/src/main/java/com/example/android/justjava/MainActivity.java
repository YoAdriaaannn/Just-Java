
package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    // global variable for amount of coffee
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        // call method to calculate price
        int price = calculatePrice();

        //Print to the log the value of whippedCream()
        Log.v("MainActivity", "The value of whippedCream is: " + whippedCream());

        //Print to the log the value of chocolate
        Log.v("MainActivity", "The value of chocolateTopping is: " + chocolateTopping());

        //Print to the log the value of getCustomerName
        Log.v("MainActivity", "The value of custName is: " + getCustomerName());

        // call the order summary, pass the price and value of the method whippedCream
        String priceMessage = createOrderSummary(price, whippedCream(), chocolateTopping(), getCustomerName());

        //display the oder summary and message
        displayMessage(priceMessage);


    }

    /**
     * method to increase quantity
     *
     * @param quantity is the amount of cups of coffee ordered
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    /**
     * method to decrease quantity
     *
     * @param quantity is the amount of cups of coffee ordered
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /* This method displays the given price on the screen.
           */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }


    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     * @param price    is the price per cup
     */

    private int calculatePrice() {

        int price = quantity * 5;
        return price;

    }

    /**
     * This method displays the given text on the screen.
     */

    private void displayMessage(String message) {

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);

        orderSummaryTextView.setText(message);


    }

    /**
     * Creates summary of order and returns summary as string.
     *
     * @param orderMessage  is the order message string in full
     * @param orderPrice    is the oder message price total
     * @param orderQuantity is the order quantity
     * @return
     */

    private String createOrderSummary(int orderPrice, boolean hasWhippedCream, boolean hasChocolate, String custName) {

        String orderMessage = "Name = " + custName + "\n" + "Add whipped cream = " + hasWhippedCream + "\n" + "Add chocolate = " + hasChocolate + "\n" + "Quantity: " + quantity + "\n" + "Total: $" + orderPrice + "\n" + "Thank you!";
        return orderMessage;

    }

    /**
     * Get the  checbox state for whipped cream.
     */

    private boolean whippedCream() {
        CheckBox orderWhippedCream = (CheckBox) findViewById(R.id.checkbox_whipped_cream);

        return orderWhippedCream.isChecked();
    }

    private boolean chocolateTopping () {
        CheckBox orderedChocolateTopping = (CheckBox) findViewById(R.id.checkbox_chocolate);
        return orderedChocolateTopping.isChecked();
    }

    private String getCustomerName () {

        EditText customerName = (EditText) findViewById(R.id.edit_name);
        return customerName.getText().toString();
    }
}
