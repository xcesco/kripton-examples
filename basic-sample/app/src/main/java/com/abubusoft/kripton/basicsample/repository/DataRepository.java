package com.abubusoft.kripton.basicsample.repository;


import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;


import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.basicsample.repository.db.BindAppDaoFactory;
import com.abubusoft.kripton.basicsample.repository.db.BindAppDataSource;
import com.abubusoft.kripton.basicsample.repository.db.dao.entity.CommentEntity;
import com.abubusoft.kripton.basicsample.repository.db.dao.entity.ProductEntity;

import java.util.List;

/**
 * Repository handling the work with products and comments.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private final BindAppDataSource dataSource;

    private MediatorLiveData<List<ProductEntity>> mObservableProducts;

    private DataRepository(final BindAppDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static DataRepository getInstance(final BindAppDataSource dataSource) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(dataSource);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public MutableLiveData<List<ProductEntity>> getProducts() {
        return dataSource.getProductDao().loadAllProducts();
    }

    public MutableLiveData<ProductEntity> loadProduct(final long productId) {
        return dataSource.getProductDao().loadProduct(productId);
    }

    public MutableLiveData<List<CommentEntity>> loadComments(final long productId) {
        return dataSource.getCommentDao().loadComments(productId);
    }

    public void removeFirstProduct() {
        dataSource.executeAsync(new BindAppDataSource.Transaction() {
            @Override
            public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
                ProductEntity bean = daoFactory.getProductDao().selectFirst();
                daoFactory.getProductDao().delete(bean);
                return TransactionResult.COMMIT;
            }
        });
    }
}
