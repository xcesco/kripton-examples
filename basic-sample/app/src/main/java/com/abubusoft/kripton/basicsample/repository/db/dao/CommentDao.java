package com.abubusoft.kripton.basicsample.repository.db.dao;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.basicsample.repository.db.dao.entity.CommentEntity;

import java.util.List;

@BindDao(CommentEntity.class)
public interface CommentDao extends AbstractDao<CommentEntity> {

    @BindSqlSelect(where="productId=:productId")
    MutableLiveData<List<CommentEntity>> loadComments(long productId);

    @BindSqlSelect(where="productId = :productId")
    List<CommentEntity> loadCommentsSync(long productId);

}