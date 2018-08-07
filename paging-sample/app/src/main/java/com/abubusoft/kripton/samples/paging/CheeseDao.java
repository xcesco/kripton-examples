package com.abubusoft.kripton.samples.paging;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;

@BindDao(Cheese.class)
public interface CheeseDao {
    /**
     * Room knows how to return a LivePagedListProvider, from which we can get a LiveData and serve
     * it back to UI via ViewModel.
     */
    @BindSqlSelect(orderBy = "name COLLATE NOCASE ASC", pageSize = 20)
    PaginatedResult<Cheese> allCheesesByName();

    @BindSqlInsert
    void insert(Cheese cheese);

    @BindSqlDelete
    void delete(Cheese cheese);
}
