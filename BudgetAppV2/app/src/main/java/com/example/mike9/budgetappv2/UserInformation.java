package com.example.mike9.budgetappv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInformation extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference();
    }
    public void onClickConfirm(View view){
        EditText mUsername = (EditText)findViewById(R.id.mUsername);
        String username = mUsername.getText().toString();
        if (username.isEmpty()){
            Toast.makeText(UserInformation.this, "Text field blank", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(UserInformation.this, "Attempting", Toast.LENGTH_SHORT).show();
            String userID = user.getUid();
            mRef.child(userID).child("name").setValue(username);
            mUsername.setText("");
        }
    }
}
