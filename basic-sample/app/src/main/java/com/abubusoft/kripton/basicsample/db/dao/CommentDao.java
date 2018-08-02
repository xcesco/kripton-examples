package com.abubusoft.kripton.basicsample.db.dao;


import android.arch.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.basicsample.db.dao.entity.CommentEntity;
import com.abubusoft.kripton.basicsample.db.dao.entity.ProductEntity;

import java.util.List;

@BindDao(CommentEntity.class)
public interface CommentDao extends AbstractDao<CommentEntity> {

    @BindSqlSelect(where="productId=:productId")
    LiveData<List<CommentEntity>> loadComments(int productId);

    @BindSqlSelect(where="productId = :productId")
    List<CommentEntity> loadCommentsSync(int productId);

}