
package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;


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

        int price = quantity * 5;
        Date todaysDate = Calendar.getInstance().getTime();
        String priceMessage = "Total: $" + price + "\n" + "Thank you!";
        priceMessage = priceMessage + "\n" + todaysDate;
        displayMessage(priceMessage);

    }

    // method to increase quantity
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    // method to decrease quantity
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
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }


    /**
     * This method displays the given text on the screen.
     */

    private void displayMessage(String message) {

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        priceTextView.setText(message);

    }


}
