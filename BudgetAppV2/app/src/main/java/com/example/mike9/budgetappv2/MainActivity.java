package com.example.mike9.budgetappv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }
    //Helper method
    public String[] getEmailAndPass(){
        String[] info = new String[2];
        EditText mEmail = (EditText)findViewById(R.id.mEmail);
        EditText mPassword = (EditText)findViewById(R.id.mPassword);
        info[0] = mEmail.getText().toString();
        info[1] = mPassword.getText().toString();
        return info;
    }

    //Regular User Signing in again
    public void btnSignIn(View view){
        String[] info = getEmailAndPass();
        if (info[0].isEmpty() || info[1].isEmpty()){
            Toast.makeText(MainActivity.this, "Email/Password empty", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(info[0], info[1])
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent welcome = new Intent(getApplicationContext(), WelcomePage.class);
                                startActivity(welcome);
                            } else{
                                Toast.makeText(MainActivity.this, "Email/Password was incorrect",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    //New User creating an account
    public void btnRegister(View view){
        String[] info = getEmailAndPass();
        if (info[0].isEmpty() || info[1].isEmpty()) {
            Toast.makeText(MainActivity.this, "Email/Password empty", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(info[0], info[1])
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent welcome = new Intent(getApplicationContext(), WelcomePage.class);
                                startActivity(welcome);
                            } else {
                                Toast.makeText(MainActivity.this, "Error creating user, please try again",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
