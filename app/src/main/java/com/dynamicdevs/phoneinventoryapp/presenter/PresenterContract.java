package com.dynamicdevs.phoneinventoryapp.presenter;

import android.content.Context;

import com.dynamicdevs.phoneinventoryapp.model.data.Phone;

import java.util.List;

public interface PresenterContract {

    interface Presenter {
        void getPhones();
        void insertNewPhone(Phone phone);
    }

    interface PhoneView {
        void displayPhones(List<Phone> phones);
        void displayError(String message);
        Context getContext();
    }
}
