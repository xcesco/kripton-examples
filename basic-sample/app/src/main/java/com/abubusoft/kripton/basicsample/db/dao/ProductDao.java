package com.abubusoft.kripton.basicsample.db.dao;

import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.basicsample.db.dao.entity.ProductEntity;

import java.util.List;

@BindDao(ProductEntity.class)
public interface ProductDao extends AbstractDao<ProductEntity> {
    @BindSqlSelect
    LiveData<List<ProductEntity>> loadAllProducts();

    @BindSqlInsert(conflictAlgorithm = ConflictAlgorithmType.REPLACE)
    void insert(ProductEntity product);

    @BindSqlSelect(where="id = :productId")
    LiveData<ProductEntity> loadProduct(int productId);

    @BindSqlSelect(where="id = :productId")
    ProductEntity loadProductSync(int productId);
}