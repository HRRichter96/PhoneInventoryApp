package com.dynamicdevs.phoneinventoryapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dynamicdevs.phoneinventoryapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.phoneInventoryButton.setOnClickListener(view ->{
            Intent intent = new Intent(this, SQLiteDatabaseActivity.class);
            startActivity(intent);
        });

    }
}