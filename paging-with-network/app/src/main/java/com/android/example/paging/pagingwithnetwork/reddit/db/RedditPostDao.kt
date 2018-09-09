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

package com.android.example.paging.pagingwithnetwork.reddit.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.abubusoft.kripton.android.annotation.BindDao
import com.abubusoft.kripton.android.annotation.BindSqlDelete
import com.abubusoft.kripton.android.annotation.BindSqlSelect
import com.abubusoft.kripton.android.livedata.PagedLiveData
import com.android.example.paging.pagingwithnetwork.reddit.vo.RedditPost

@BindDao(RedditPost::class)
interface RedditPostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post : List<RedditPost>)

    @BindSqlSelect(where="subreddit = :subreddit", orderBy = "indexInResponse ASC")
    fun postsBySubreddit(subreddit : String) : PagedLiveData<RedditPost>

    @BindSqlDelete(where="subreddit = :subreddit")
    fun deleteBySubreddit(subreddit: String)

    @BindSqlSelect(jql="SELECT MAX(indexInResponse) + 1 FROM posts WHERE subreddit = :subreddit")
    fun getNextIndexInSubreddit(subreddit: String) : Int
}