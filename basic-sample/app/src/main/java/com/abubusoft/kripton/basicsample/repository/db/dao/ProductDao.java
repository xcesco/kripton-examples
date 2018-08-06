package com.abubusoft.kripton.basicsample.repository.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.basicsample.repository.db.dao.entity.ProductEntity;

import java.util.List;

@BindDao(ProductEntity.class)
public interface ProductDao extends AbstractDao<ProductEntity> {
    @BindSqlSelect(orderBy = "name")
    MutableLiveData<List<ProductEntity>> loadAllProducts();

    @BindSqlSelect(where = "id = :productId")
    MutableLiveData<ProductEntity> loadProduct(long productId);

    @BindSqlSelect(where = "id = :productId")
    ProductEntity loadProductSync(long productId);

    @BindSqlSelect(orderBy = "name", pageSize = 1)
    ProductEntity selectFirst();

    @BindSqlDelete(where = "id=:{bean.id}")
    int delete(ProductEntity bean);

}