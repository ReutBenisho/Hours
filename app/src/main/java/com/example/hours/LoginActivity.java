package com.example.hours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView mLblRegister;
    private TextView mLblSignInAsGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLblRegister = findViewById(R.id.lbl_create_account);
        mLblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        mLblSignInAsGuest = findViewById(R.id.lbl_sign_in_as_guest);
        mLblSignInAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ExitTimeActivity.class));
            }
        });
    }
}