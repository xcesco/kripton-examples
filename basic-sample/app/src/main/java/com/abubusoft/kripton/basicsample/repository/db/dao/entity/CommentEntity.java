package com.abubusoft.kripton.basicsample.repository.db.dao.entity;


import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import com.abubusoft.kripton.basicsample.model.Comment;
import com.abubusoft.kripton.basicsample.model.Product;

import java.util.Date;


@BindSqlType(name = "comments")
public class CommentEntity implements Comment {

    private long id;

    @BindSqlColumn(parentEntity = ProductEntity.class, onDelete = ForeignKeyAction.CASCADE, columnType = ColumnType.INDEXED)
    private long productId;

    private String text;
    private Date postedAt;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public CommentEntity() {
    }

    public CommentEntity(int id, int productId, String text, Date postedAt) {
        this.id = id;
        this.productId = productId;
        this.text = text;
        this.postedAt = postedAt;
    }
}