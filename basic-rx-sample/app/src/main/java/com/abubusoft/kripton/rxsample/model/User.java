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

package com.abubusoft.kripton.rxsample.model;

import android.support.annotation.NonNull;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

import java.util.UUID;

/**
 * Immutable model class for a User
 */
@BindSqlType(name = "users")
public class User {

    @BindSqlColumn(value = "userid", columnType = ColumnType.PRIMARY_KEY)
    private String id;

    @BindSqlColumn(value = "username")
    private String userName;

    public User(String userName) {
        id = UUID.randomUUID().toString();
        this.userName = userName;
    }

    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
