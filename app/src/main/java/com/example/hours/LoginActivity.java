package com.example.hours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {
    private void openBiometricPrompt(){
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.biometric_authentication))
                .setSubtitle(getString(R.string.login_using_fingerprint))
                .setNegativeButtonText(getString(R.string.enter_password_instead))
                .build();
        biometricPrompt.authenticate(promptInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferencesUtil.loadDefaults(getApplicationContext());
        if(SharedPreferencesUtil.getString("existing_user", getApplicationContext()) == "true"){

        }
        else{
            //openBiometricPrompt();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferencesUtil.loadDefaults(getApplicationContext());
        if(SharedPreferencesUtil.getString("existing_user", getApplicationContext()) == "true"){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        else {
            TextView lblRegister = findViewById(R.id.lbl_create_account);
            lblRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                }
            });

            TextView lblSignInAsGuest = findViewById(R.id.lbl_sign_in_as_guest);
            lblSignInAsGuest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            });
        }

    }
}