package com.abubusoft.kripton.rxsample.persistence;

import com.abubusoft.kripton.rxsample.model.User;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface LocalRepository {
    Observable<User> getUser();

    void insertOrUpdateUser(User user);

    void deleteAllUsers();
}
