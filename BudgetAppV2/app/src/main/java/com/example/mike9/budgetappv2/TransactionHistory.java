package com.example.mike9.budgetappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TransactionHistory extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        //Carries over the user information
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }
    public void onClickAddTransaction(View view){
        Intent nextPage = new Intent(getApplicationContext(),TransactionInput.class);
        startActivity(nextPage);
    }
}
