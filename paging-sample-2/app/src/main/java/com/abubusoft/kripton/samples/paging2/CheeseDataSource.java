package com.abubusoft.kripton.samples.paging2;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;

@BindDataSourceOptions(populator = CheesePopulator.class)
@BindDataSource(daoSet = {CheeseDao.class}, fileName = "cheese.db")
public interface CheeseDataSource {
}
