package com.abubusoft.kripton.basicsample.db.dao;

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.sqlite.SQLitePopulator;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.basicsample.db.BindAppDataSource;
import com.abubusoft.kripton.basicsample.db.DataGenerator;
import com.abubusoft.kripton.basicsample.db.dao.entity.CommentEntity;
import com.abubusoft.kripton.basicsample.db.dao.entity.ProductEntity;

import java.util.List;

public class AppDatabasePopulator implements SQLitePopulator {
    /**
     * Execute
     *
     * @param database
     */
    @Override
    public void execute(SQLiteDatabase database) {
        addDelay();
        List<ProductEntity> products = DataGenerator.generateProducts();
        List<CommentEntity> comments =
                DataGenerator.generateCommentsForProducts(products);

        insertData(products, comments);
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    private static void insertData(final List<ProductEntity> products,
                                   final List<CommentEntity> comments) {


        BindAppDataSource.getInstance().executeAsync(daoFactory -> {

            for (ProductEntity item: products) {
                daoFactory.getProductDao().insert(item);
            }

            for (CommentEntity item: comments) {
                daoFactory.getCommentDao().insert(item);
            }

            return TransactionResult.COMMIT;
        });

    }
}
