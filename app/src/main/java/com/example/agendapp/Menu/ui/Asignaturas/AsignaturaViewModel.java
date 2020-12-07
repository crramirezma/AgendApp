package com.example.agendapp.Menu.ui.Asignaturas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AsignaturaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AsignaturaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}