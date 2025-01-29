package com.example.waterparkgrid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.editText);  // Email input field
        passwordInput = findViewById(R.id.editText2);  // Password input field
        mAuth = FirebaseAuth.getInstance();  // Firebase Authentication instance
    }

    /**
     * Called when the user clicks the login button.
     */
    public void loginUser(View v) {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Check if the email and password are empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Attempt to sign in with Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login successful, navigate to MainActivity
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                            navigateToMain();
                        } else {
                            // Login failed, show error message
                            Toast.makeText(getApplicationContext(), "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * Navigates to MainActivity after successful login.
     */
    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Close LoginActivity so it doesn't stay in the back stack
    }

    /**
     * Navigates to the RegisterActivity.
     */
    public void openRegister(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    // Back button method to return to the previous activity
    public void goBack(View v) {
        finish(); // Close the current activity and go back to the previous one
    }
}
