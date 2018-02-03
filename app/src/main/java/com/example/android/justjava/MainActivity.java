package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    int sugar_quantity = 0;
    int milk_quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
        }
        display(quantity);
    }

    public void includeSugar(View view) {
        boolean isChecked = ((ToggleButton) view).isChecked();
        if (isChecked) {
            sugar_quantity = quantity;
        } else {
            sugar_quantity = 0;
        }
    }

    public void includeMilk(View view) {
        boolean isChecked = ((ToggleButton) view).isChecked();
        if (isChecked) {
            milk_quantity = quantity;
        } else {
            milk_quantity = 0;
        }
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        int total = 0;
        total = quantity * 5 + sugar_quantity + milk_quantity;
        displayPrice(total);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given value on the screen
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }



}