package com.abubusoft.filmfinder.service.model;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;

@BindSharedPreferences(liveData = true)
public class AppSharedPreferences {

    public String displayName="Will Smith";

    public FriendListType friendList=FriendListType.ALWAYS;
}
