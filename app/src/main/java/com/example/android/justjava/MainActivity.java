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
import android.widget.ToggleButton;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    boolean hasSugar = false;
    boolean hasMilk = false;
    boolean hasWhippedCream = false;
    boolean hasChocolate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showOnMap(View view) {
        Uri cafeLocation = Uri.parse("geo:0,0?q=Ul. Krste Hegedušića 11, Sesvete");
        Intent showLocation = new Intent(Intent.ACTION_VIEW, cafeLocation);
        showLocation.setPackage("com.google.android.apps.maps");
        if (showLocation.resolveActivity(getPackageManager()) != null) {
            startActivity(showLocation);
        }
    }


    public void increment(View view) {
        if (quantity == 100) {
            Toast noAboveHundred = Toast.makeText(getApplicationContext(),getString(R.string.toast_above100),Toast.LENGTH_LONG);
            noAboveHundred.show();
        }
        if (quantity < 100) {
            quantity += 1;
        }
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast noBelowOne = Toast.makeText(getApplicationContext(),getString(R.string.toast_subzero),Toast.LENGTH_LONG);
            noBelowOne.show();
        }
        if (quantity > 1) {
            quantity -= 1;
        }
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        EditText nameText = (EditText) findViewById(R.id.name_field);
        String name = nameText.getText().toString();
        //Intent sendOrderByMail = new Intent(Intent.ACTION_SEND);
        Intent sendOrderByMail = new Intent(Intent.ACTION_SENDTO);
        String toAddress = "mazen426@gmail.com";
        String subject = getString(R.string.order_subject,name);
        String message = createOrderSummary(calculatePrice(quantity),hasWhippedCream,hasChocolate);
        sendOrderByMail.setType("text/plain");
        sendOrderByMail.setData(Uri.parse("mailto:"));
        sendOrderByMail.putExtra(Intent.EXTRA_EMAIL,new String[]{toAddress});
        sendOrderByMail.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendOrderByMail.putExtra(Intent.EXTRA_TEXT, message);
        if (sendOrderByMail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendOrderByMail);
        }
    }

    public int calculatePrice(int numberOfCups) {
        int cupPrice = 5;

        if (hasWhippedCream) {
            cupPrice += 1;
        }

        if (hasChocolate) {
            cupPrice += 2;
        }

        if (hasSugar) {
            cupPrice += 1;
        }

        if (hasMilk) {
            cupPrice += 1;
        }
        int price = numberOfCups * cupPrice;
        return price;
    }

    public void hasWhippedCream(View view) {
        CheckBox whipCheck = (CheckBox) findViewById(R.id.whip);
        hasWhippedCream = whipCheck.isChecked();
    }

    public void hasChocolate(View view) {
        CheckBox whipCheck = (CheckBox) findViewById(R.id.choco);
        hasChocolate = whipCheck.isChecked();
    }

    public void includeSugar(View view) {
        ToggleButton sugarToggle = (ToggleButton) findViewById(R.id.sugar_button);
        hasSugar = sugarToggle.isChecked();
    }

    public void includeMilk(View view) {
        ToggleButton milkToggle = (ToggleButton) findViewById(R.id.milk_button);
        hasMilk = milkToggle.isChecked();
    }

    private String createOrderSummary(int priceOfOrder , boolean hasWhippedCream, boolean hasChocolate) {
        EditText nameText = (EditText) findViewById(R.id.name_field);
        String name = nameText.getText().toString();
        return getString(R.string.order_summary) + "\n" +
                getString(R.string.order_summary_name,name) + "\n" +
                getString(R.string.order_quantity) + quantity +"\n" +
                getString(R.string.cream_bar) + hasWhippedCream + "\n" +
                getString(R.string.chocolate_bar) + hasChocolate + "\n" +
                getString(R.string.total) + priceOfOrder + "\n" +
                getString(R.string.thank_you);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}