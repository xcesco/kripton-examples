package com.abubusoft.kripton.samples.paging;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.abubusoft.kripton.android.sqlite.PaginatedResult;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

public class CheeseViewModel extends AndroidViewModel {

    private final BindCheeseDataSource dataSource;

    public CheeseViewModel(@NonNull Application application) {
        super(application);

        this.dataSource=((SampleApplication)application).getDataSource();
    }

    public void insert(String text) {
        dataSource.executeAsync(daoFactory -> {
            daoFactory.getCheeseDao().insert(new Cheese(text));
            return TransactionResult.COMMIT;
        });
    }

    public void remove(Cheese cheese) {
        dataSource.executeAsync(daoFactory -> {
            daoFactory.getCheeseDao().delete(cheese);
            return TransactionResult.COMMIT;
        });
    }

    public LiveData<PaginatedResult<Cheese>> getList() {
        return dataSource.getCheeseDao().allCheesesByName();
    }

}
