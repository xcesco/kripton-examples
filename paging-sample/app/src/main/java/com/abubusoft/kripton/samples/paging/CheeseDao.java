package com.abubusoft.kripton.samples.paging;

import androidx.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.livedata.PagedLiveData;

import java.util.List;

@BindDao(Cheese.class)
public interface CheeseDao {

    @BindSqlSelect(orderBy = "name COLLATE NOCASE ASC", pageSize = 20)
    PagedLiveData<List<Cheese>> allCheesesByName();

    @BindSqlSelect(fields = "count(*)")
    LiveData<Integer> countAllCheeses();


    @BindSqlInsert
    void insert(Cheese cheese);

    @BindSqlDelete(where="id=:cheese.id")
    void delete(Cheese cheese);
}
