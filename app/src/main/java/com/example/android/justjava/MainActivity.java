
package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    // variable for amount of coffee
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


        // Check to see if user ordered whipped cream.
        CheckBox orderWhippedCream = findViewById(R.id.checkbox_whipped_cream);
        Boolean hasWhippedCream = orderWhippedCream.isChecked();

        // Check to see if user ordered chocolate.
        CheckBox orderedChocolateTopping = findViewById(R.id.checkbox_chocolate);
        Boolean hasChocolate = orderedChocolateTopping.isChecked();

        // Grab a name from the user
        EditText customerName = findViewById(R.id.edit_name);
        String name = customerName.getText().toString();


        // Calculate the price.
        int price = calculatePrice(quantity, hasWhippedCream, hasChocolate);

        // Send order summary via e mail.
        String summary = getString(R.string.name_tag) + name + "\n" + getString(R.string.whipped_cream_tag) + hasWhippedCream + "\n" + getString(R.string.chocolate_tag) + hasChocolate + "\n" + getString(R.string.quantity_tag) + quantity + "\n" + getString(R.string.total_tag) + price + "\n" + getString(R.string.thank_you_tag);
        Intent sendMail = new Intent(Intent.ACTION_SEND);
        sendMail.setData(Uri.parse("mailto:"));
        sendMail.setType("*/*");
        sendMail.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        sendMail.putExtra(Intent.EXTRA_TEXT, summary);
        if (sendMail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendMail);
        }


    }

    /**
     * Method to increase quantity.
     */
    public void increment(View view) {
        if (quantity >= 100) {

            Toast.makeText(this, getString(R.string.msg_coffee), Toast.LENGTH_SHORT).show();

        } else {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }

    /**
     * Method to decrease quantity.
     */

    public void decrement(View view) {
        if (quantity <= 1) {
            Toast.makeText(this,getString(R.string.msg_coffee), Toast.LENGTH_SHORT).show();

        } else {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" " + numberOfCoffees);
    }


    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */

    private int calculatePrice(int quantity, boolean whipStatus, boolean chocStatus) {

        // Set up some variables to store the prices of items
        int coffeePrice = 5;
        int whippedCreamPrice = 1;
        int chocolatePrice = 2;


        // Check to see if whipped cream or chocolate has been added to order. If so adjust price of coffee.
        if (whipStatus) {
            coffeePrice = coffeePrice + whippedCreamPrice;
        }
        if (chocStatus) {
            coffeePrice = coffeePrice + chocolatePrice;
        }
        return coffeePrice * quantity;

    }


}
