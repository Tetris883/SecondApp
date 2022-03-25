package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;
    int price = 0;

    public void increase(View view){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantity+=1;
        quantityTextView.setText(""+(quantity));
    }

    public void decrease(View view){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantity-=1;
        quantityTextView.setText(""+(quantity));
    }

    public void submitOrder(View view) {
        display(quantity);
        displayPrice();
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        price = quantity*50;
        priceTextView.setText("Total: "+NumberFormat.getCurrencyInstance().format(price) + "\nThank you!!!");
    }
}