package com.dynamicdevs.phoneinventoryapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dynamicdevs.phoneinventoryapp.R;
import com.dynamicdevs.phoneinventoryapp.databinding.ActivitySqliteDatabaseBinding;
import com.dynamicdevs.phoneinventoryapp.model.data.Phone;
import com.dynamicdevs.phoneinventoryapp.model.db.PhoneDBHelper;
import com.dynamicdevs.phoneinventoryapp.presenter.PhonePresenter;
import com.dynamicdevs.phoneinventoryapp.presenter.PresenterContract;
import com.dynamicdevs.phoneinventoryapp.view.adapter.PhoneAdapter;

import java.util.List;

public class SQLiteDatabaseActivity extends AppCompatActivity implements PhoneAdapter.PhoneDelegate, PresenterContract.PhoneView {

    private ActivitySqliteDatabaseBinding binding;
    private Phone.Manufacturer setManufacturer = Phone.Manufacturer.APPLE;
    private PhoneAdapter phoneAdapter = new PhoneAdapter(this);
    private PresenterContract.Presenter phonePresenter;

    private PhoneDBHelper dbHelper;

    private String [] options = {
            Phone.Manufacturer.APPLE.name(),
            Phone.Manufacturer.SAMSUNG.name(),
            Phone.Manufacturer.GOOGLE.name(),
            Phone.Manufacturer.HUAWEI.name(),
            Phone.Manufacturer.LG.name(),
            Phone.Manufacturer.MOTOROLA.name(),
            Phone.Manufacturer.VIVO.name()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySqliteDatabaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.phoneListview.setAdapter(phoneAdapter);
        phonePresenter = new PhonePresenter(this);

        dbHelper = new PhoneDBHelper(this);
        Phone phone = getIntent().getParcelableExtra("DELETE_PHONE_DATA");
        if(phone != null) {
            dbHelper.deletePhone(phone);
            phone = null;
        }
        readAllPhones();

        binding.manufacturerSpinner.setAdapter( new ArrayAdapter<String>(this, R.layout.spinner_item,R.id.manufacturer_name, options ));
        binding.manufacturerSpinner.setSelection(0);

        binding.manufacturerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setManufacturer = Phone.Manufacturer.valueOf(options[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//  Do  nothing
            }
        });
        binding.insertPhoneButton.setOnClickListener(view -> {
            String phoneModel = binding.modelEdittext.getText().toString().trim();
            double price = Double.parseDouble(binding.newPriceEditview.getText().toString());
            Phone newPhone = new Phone(setManufacturer, phoneModel, price);
            dbHelper.insertPhone(newPhone);
            readAllPhones();
        });

        binding.manufacturerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setManufacturer = Phone.Manufacturer.valueOf(options[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//  Do  nothing
            }
        });
    }

    private void readAllPhones() {
        phoneAdapter.setPhoneList(dbHelper.getAllPhones());
    }

    @Override
    public void selectPhone(Phone phone) {
        Toast.makeText(this, phone.getModel(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, PhoneDetailsActivity.class);
        intent.putExtra("PHONE_DATA", phone);
        startActivity(intent);
    }

    @Override
    public void displayPhones(List<Phone> phones) {
        phoneAdapter.setPhoneList(phones);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }


}
