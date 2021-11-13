package com.example.androidprojet.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.androidprojet.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity_ extends LoginActivity {

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        ActivityRegisterBinding binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText email = binding.EmailEditText;
        final EditText password = binding.PasswordEditText;
        final Button RegisterButton = binding.register;
        final TextView ErrorMessage = binding.ErrorMessage;


        RegisterButton.setOnClickListener(v -> {
                    if (!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
                        Register(email.getText().toString(), password.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity_.this, "Login ou Mot De Passe incorrect",
                                Toast.LENGTH_SHORT).show();
                        ErrorMessage.setVisibility(View.VISIBLE);
                    }
                }
        );
    }

    private void updateUiWithUser(FirebaseUser user) {

        if (user != null) {
            startActivity(new Intent(RegisterActivity_.this, LoginActivity.class));
        }
    }

    public void Register(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    private static final String TAG = "register";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUiWithUser(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity_.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUiWithUser(null);
                        }
                    }
                });
    }


}




