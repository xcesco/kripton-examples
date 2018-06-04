package com.abubusoft.sharedpreferencewatcher.viewmodel;

import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.sharedpreferencewatcher.model.BindAppPreferences;
import com.abubusoft.sharedpreferencewatcher.model.ListType;

public class SharedPrefsViewModel extends android.arch.lifecycle.ViewModel {

    BindAppPreferences prefs;

    public SharedPrefsViewModel() {
        prefs=BindAppPreferences.getInstance();
    }

    public LiveData<Boolean> getExampleSwitch() {
        return prefs.getExampleSwitchAsLiveData();
    }

    public LiveData<String> getExampleText() {
        return prefs.getExampleTextAsLiveData();
    }

    public LiveData<ListType> getExampleList() {
        return prefs.getExampleListAsLiveData();
    }

}
