/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abubusoft.kripton.rxsample.persistence;

import com.abubusoft.kripton.android.SqlModificationType;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.rxsample.model.User;
import com.abubusoft.kripton.rxsample.persistence.db.BindUsersDaoFactory;
import com.abubusoft.kripton.rxsample.persistence.db.BindUsersDataSource;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Using the Room database as a data source.
 */
public class LocalRepositoryImpl implements LocalRepository {

    private final BindUsersDataSource dataSource;

    public LocalRepositoryImpl(BindUsersDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Observable<User> getUser() {
        return dataSource.getUserSubject().map(t -> {
            User createdUser = null;
            if (t.getOperationType() == SqlModificationType.INSERT) {
                String id = t.getLastInsertedUid();

                createdUser = dataSource.getUserDao().getUser(id);

            }
            return createdUser;

        });
    }

    @Override
    public void insertOrUpdateUser(User user) {
        dataSource.execute(daoFactory -> {
            dataSource.getUserDao().insertUser(user);

            return TransactionResult.COMMIT;
        });
    }

    @Override
    public void deleteAllUsers() {
        dataSource.getUserDao().deleteAllUsers();
    }
}
