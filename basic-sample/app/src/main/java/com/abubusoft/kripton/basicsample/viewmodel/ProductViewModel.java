package com.abubusoft.kripton.basicsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;


import com.abubusoft.kripton.basicsample.BasicApp;
import com.abubusoft.kripton.basicsample.db.BindAppDataSource;
import com.abubusoft.kripton.basicsample.db.dao.entity.CommentEntity;
import com.abubusoft.kripton.basicsample.db.dao.entity.ProductEntity;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private final LiveData<ProductEntity> mObservableProduct;

    public ObservableField<ProductEntity> product = new ObservableField<>();

    private final int mProductId;

    private final LiveData<List<CommentEntity>> mObservableComments;

    public ProductViewModel(@NonNull Application application, BindAppDataSource repository,
            final int productId) {
        super(application);
        mProductId = productId;

        mObservableComments = repository.getCommentDao().loadComments(mProductId);
        mObservableProduct = repository.getProductDao().loadProduct(mProductId);
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    public LiveData<List<CommentEntity>> getComments() {
        return mObservableComments;
    }

    public LiveData<ProductEntity> getObservableProduct() {
        return mObservableProduct;
    }

    public void setProduct(ProductEntity product) {
        this.product.set(product);
    }

    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mProductId;

        private final BindAppDataSource mRepository;

        public Factory(@NonNull Application application, int productId) {
            mApplication = application;
            mProductId = productId;
            mRepository = ((BasicApp) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ProductViewModel(mApplication, mRepository, mProductId);
        }
    }
}