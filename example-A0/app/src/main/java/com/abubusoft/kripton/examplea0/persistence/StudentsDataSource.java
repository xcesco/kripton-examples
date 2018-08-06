package com.abubusoft.kripton.examplea0.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 30/08/2017.
 */
@BindDataSource(fileName = "students.db", daoSet = {StudentDao.class},generateAsyncTask = true, generateSchema = true)
public interface StudentsDataSource {
}
