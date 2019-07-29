package com.abubusoft.kripton.samples.paging2;

import androidx.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.androidx.livedata.PagedLiveData;

import java.util.List;

@BindDao(Cheese.class)
public interface CheeseDao {
    /**
     * Room knows how to return a LivePagedListProvider, from which we can get a LiveData and serve
     * it back to UI via ViewModel.
     */
    @BindSqlSelect(orderBy = "name COLLATE NOCASE ASC", pageSize = 50)
    PagedLiveData<List<Cheese>> allCheesesByName();

    @BindSqlSelect(orderBy = "name COLLATE NOCASE ASC", pageSize = 50)
    List<Cheese> findAllCheesesByName();

    @BindSqlSelect(fields = "count(*)")
    LiveData<Integer> countAllCheeses();


    @BindSqlInsert
    void insert(Cheese cheese);

    @BindSqlDelete(where="id=:cheese.id")
    void delete(Cheese cheese);
}
