package com.example.androidprojet.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidprojet.databinding.ActivitySynonymBinding;

public class SynonymActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySynonymBinding binding = ActivitySynonymBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}