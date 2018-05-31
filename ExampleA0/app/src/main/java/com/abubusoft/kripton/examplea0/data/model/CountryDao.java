package com.abubusoft.kripton.examplea0.data.model;


import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;


import java.util.List;

/**
 * Created by xcesco on 26/02/2017.
 */
@BindDao(Country.class)
public interface CountryDao extends AbstractDao<Country> {

    @BindSqlSelect(orderBy = "name asc")
    List<Country> selectAll();

    @BindSqlSelect(where ="callingCode = ${callingCode}")
    Country selectByCallingCode(String callingCode);

    @BindSqlSelect(where ="code = ${code}")
    Country selectByCountry(String code);
}
