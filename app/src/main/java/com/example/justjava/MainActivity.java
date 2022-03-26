package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int quantity = 1;
    int price = 0;

    public boolean hasWhippedCream = false;
    public boolean hasChocolate = false;

    public void increase(View view){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        Button incButton = (Button) findViewById(R.id.increment);
        quantity += 1;
        quantityTextView.setText(""+(quantity));
    }

    public void decrease(View view){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        Button decButton = (Button) findViewById(R.id.decrement);
        if(quantity <= 1)
            quantity = 1;
        else
            quantity-=1;
        quantityTextView.setText(""+(quantity));
    }

    public void submitOrder(View view) {
        display(quantity);
        mailOrderSummary();
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private void mailOrderSummary() {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);

        price = calculatePrice(quantity);

        EditText txt = (EditText) findViewById(R.id.name);
        if(txt.getText().toString()=="")
            txt.setText("NoName");
        String name = txt.getText().toString();

        String orderSummary = "";
        orderSummary += "Name: " + name;
        orderSummary += "\nQuantity: " + quantity;
        if(whippedCreamCheckBox.isChecked()){
            price += 3*quantity;
            hasWhippedCream = true;
            orderSummary += "\nAdded whipped cream";
        }
        if(chocolateCheckBox.isChecked()){
            price += 5*quantity;
            hasChocolate = true;
            orderSummary += "\nAdded chocolate";
        }
        orderSummary += "\nPrice: " + NumberFormat.getCurrencyInstance().format(price);
        orderSummary += "\nThank you!";

        orderSummaryTextView.setText(orderSummary);
        composeEmail(orderSummary, ("Order Summary for " + name));
    }

    public void composeEmail(String text, String subject) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",subject, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
            Log.v("MainActivity", "Sharing the order summary to email");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Log.d("MainActivity", "No email app installed!");
            Toast.makeText(MainActivity.this, "Uh...No email app?", Toast.LENGTH_SHORT).show();
        }
    }

    private int calculatePrice(int q){
        int p = 50;
        int total = q*p;
        return total;
    }

    protected boolean isEmpty(EditText editText) {
        return (editText.getText().toString().equals(""));
    }

}