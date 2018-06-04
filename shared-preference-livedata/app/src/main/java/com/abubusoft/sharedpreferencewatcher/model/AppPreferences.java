package com.abubusoft.sharedpreferencewatcher.model;

import com.abubusoft.kripton.android.annotation.BindPreference;
import com.abubusoft.kripton.android.annotation.BindPreferenceAdapter;
import com.abubusoft.kripton.android.annotation.BindSharedPreferences;

@BindSharedPreferences(liveData = true )
public class AppPreferences {

    public boolean exampleSwitch=false;

    @BindPreferenceAdapter(adapter = UpperAdapter.class)
    public String exampleText="TONJ MANERO";

    public ListType exampleList=ListType.ALWAYS;

    @BindPreference(liveData = false)
    public String syncFrequency="15";

    @BindPreference(liveData = false)
    public String notificationsNewMessage="content://settings/system/notification_sound";

    @BindPreference(liveData = false)
    public boolean notificationsNewMessageVibrate=false;

}
