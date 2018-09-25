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

package com.android.example.paging.pagingwithnetwork.reddit.vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.abubusoft.kripton.android.ColumnType
import com.abubusoft.kripton.android.annotation.BindIndex
import com.abubusoft.kripton.android.annotation.BindSqlColumn
import com.abubusoft.kripton.android.annotation.BindSqlType
import com.abubusoft.kripton.annotation.BindType
import com.google.gson.annotations.SerializedName

@BindType
@BindSqlType(name="posts")
data class RedditPost(
        @BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
        val name: String,

        val title: String,

        val score: Int,

        val author: String,

        @BindSqlColumn(columnType = ColumnType.INDEXED)
        val subreddit: String,

        val numComments: Int,

        @BindSqlColumn("created_utc")
        val created: Long,

        val thumbnail: String?,

        val url: String?,

        var indexInResponse: Int = -1) {
    // to be consistent w/ changing backend order, we need to keep a data like this

}