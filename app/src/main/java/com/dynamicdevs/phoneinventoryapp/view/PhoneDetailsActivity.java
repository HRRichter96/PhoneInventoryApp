package com.dynamicdevs.phoneinventoryapp.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dynamicdevs.phoneinventoryapp.databinding.ActivityPhoneDetailsBinding;
import com.dynamicdevs.phoneinventoryapp.model.data.Phone;
import com.dynamicdevs.phoneinventoryapp.model.db.PhoneDBHelper;

public class PhoneDetailsActivity extends AppCompatActivity {

    private ActivityPhoneDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Phone phone = getIntent().getParcelableExtra("PHONE_DATA");
        if(phone != null){
            binding.phoneModelTextview.setText(phone.getModel());
            binding.phonePriceTextview.setText("$" + phone.getPrice());

            binding.sellButton.setOnClickListener(view -> {
                Intent intent = new Intent(this, SQLiteDatabaseActivity.class);
                intent.putExtra("DELETE_PHONE_DATA", phone);
                startActivity(intent);
            });
        }


    }
}
