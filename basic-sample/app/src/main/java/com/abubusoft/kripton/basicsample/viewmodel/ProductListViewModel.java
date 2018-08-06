/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abubusoft.kripton.basicsample.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;

import com.abubusoft.kripton.basicsample.BasicApp;
import com.abubusoft.kripton.basicsample.repository.DataRepository;
import com.abubusoft.kripton.basicsample.repository.db.dao.entity.ProductEntity;

import java.util.List;

public class ProductListViewModel extends AndroidViewModel {

    private final DataRepository repository;

    public ProductListViewModel(Application application) {
        super(application);

        repository=((BasicApp) application).getRepository();

        // observe the changes of the products from the database and forward them
        //mObservableProducts.addSource(products, mObservableProducts::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<ProductEntity>> getProducts() {
        return  repository.getProducts();
    }

    public void removeFirstProduct() {
        repository.removeFirstProduct();
    }
}
