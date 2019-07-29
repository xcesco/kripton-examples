package com.abubusoft.kripton.samples.paging2;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import java.util.List;

public class CheeseViewModel extends AndroidViewModel {
    public com.abubusoft.kripton.androidx.livedata.PagedLiveData<List<Cheese>> getAllCheeses() {
        return this.dataSource.getCheeseDao().allCheesesByName();
    }

    public BindCheeseDataSource getDataSource() {
        return dataSource;
    }

    private final BindCheeseDataSource dataSource;

    public CheeseViewModel(@NonNull Application application) {
        super(application);

        this.dataSource=((SampleApplication)application).getDataSource();

    }

    public void insert(String text) {
        dataSource.executeAsync(daoFactory -> {
            daoFactory.getCheeseDao().insert(new Cheese(text, null));
            return TransactionResult.COMMIT;
        });
    }

    public void remove(Cheese cheese) {
        dataSource.executeAsync(daoFactory -> {
            daoFactory.getCheeseDao().delete(cheese);
            return TransactionResult.COMMIT;
        });
    }
}
