package com.dynamicdevs.phoneinventoryapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dynamicdevs.phoneinventoryapp.databinding.PhoneItemLayoutBinding;
import com.dynamicdevs.phoneinventoryapp.model.data.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneAdapter extends BaseAdapter {

    public interface PhoneDelegate{
        public void selectPhone (Phone phone);
    }

    private List<Phone> phoneList = new ArrayList<>();

    private PhoneDelegate phoneDelegate;

    public void setPhoneList(List<Phone> phoneList){
        this.phoneList = phoneList;
        notifyDataSetChanged();
    }

    public PhoneAdapter(PhoneDelegate phoneDelegate) {this.phoneDelegate = phoneDelegate;}

    public PhoneAdapter(List<Phone> phoneList, PhoneDelegate phoneDelegate) {
        this.phoneList = phoneList;
        this.phoneDelegate = phoneDelegate;
    }

    @Override
    public int getCount() {
        return phoneList.size();
    }

    @Override
    public Object getItem(int i) {
        return phoneList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long)i;
    }

    private PhoneItemLayoutBinding binding;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = PhoneItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        Phone phone = phoneList.get(i);
        binding.manufacturerTextview.setText("Manufacturer: " +phone.getManufacturer());
        binding.modelTextview.setText(phone.getModel());
        binding.priceTextview.setText("$" + String.valueOf(phone.getPrice()));

        binding.getRoot().setOnClickListener(v -> {
            phoneDelegate.selectPhone(phone);
        });
        return binding.getRoot();
    }
}
