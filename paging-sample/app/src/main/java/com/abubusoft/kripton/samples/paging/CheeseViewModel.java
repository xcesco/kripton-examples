package com.abubusoft.kripton.samples.paging;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

public class CheeseViewModel extends AndroidViewModel {
    public BindCheeseDataSource getDataSource() {
        return dataSource;
    }

    private final BindCheeseDataSource dataSource;

    public CheeseViewModel(@NonNull Application application) {
        super(application);

        this.dataSource=((SampleApplication)application).getDataSource();
    }

    public void insert(String text) {
        dataSource.executeAsync(new BindCheeseDataSource.Transaction() {
            @Override
            public TransactionResult onExecute(BindCheeseDaoFactory daoFactory) {
                daoFactory.getCheeseDao().insert(new Cheese(text));
                return TransactionResult.COMMIT;
            }
        });
    }

    public void remove(Cheese cheese) {
        dataSource.executeAsync(new BindCheeseDataSource.Transaction() {
            @Override
            public TransactionResult onExecute(BindCheeseDaoFactory daoFactory) {
                daoFactory.getCheeseDao().delete(cheese);
                return TransactionResult.COMMIT;
            }
        });
    }
}
