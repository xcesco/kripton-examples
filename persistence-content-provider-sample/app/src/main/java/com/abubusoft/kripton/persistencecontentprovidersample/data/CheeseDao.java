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

package com.abubusoft.kripton.persistencecontentprovidersample.data;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import java.util.List;


/**
 * Data access object for Cheese.
 */
@BindContentProviderPath(path = "cheese")
@BindDao(Cheese.class)
public interface CheeseDao {

    @BindSqlSelect(fields="count(*)")
    int count();

    @BindContentProviderEntry
    @BindSqlInsert
    long insert(String name);

    @BindContentProviderEntry()
    @BindSqlSelect
    List<Cheese> selectAll();

    @BindContentProviderEntry(path = "${id}")
    @BindSqlSelect(where ="id=${id}")
    Cheese selectById(long id);

    @BindContentProviderEntry(path = "${id}")
    @BindSqlDelete(where ="id=${id}")
    int deleteById(long id);

    @BindContentProviderEntry(path = "${cheese.id}")
    @BindSqlUpdate(where="id=${cheese.id}")
    int update(Cheese cheese);

}