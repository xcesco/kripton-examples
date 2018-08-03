package com.abubusoft.kripton.examplea0.recipes.v3.persistence;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.examplea0.recipes.v3.model.AuthorV3;

/**
 * Created by xcesco on 01/09/2017.
 */
@BindContentProviderPath(path = "authors")
@BindDao(AuthorV3.class)
public interface AuthorV3Dao extends BaseV3Dao<AuthorV3> {

}
