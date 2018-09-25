package com.abubusoft.kripton.samples.paging;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.abubusoft.kripton.android.livedata.PagedLiveData;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import java.util.List;

public class CheeseViewModel extends AndroidViewModel {
    private final LiveData<Integer> cheeseCount;

    public PagedLiveData<List<Cheese>> getAllCheeses() {
        return allCheeses;
    }

    public LiveData<Integer> getCheeseCount() {
        return Transformations.map(cheeseCount, count -> (count / allCheeses.getPageSize() + ((count % allCheeses.getPageSize())>0 ? 1 :0)));
    }

    private final PagedLiveData<List<Cheese>> allCheeses;

    public BindCheeseDataSource getDataSource() {
        return dataSource;
    }

    private final BindCheeseDataSource dataSource;

    public CheeseViewModel(@NonNull Application application) {
        super(application);

        this.dataSource=((SampleApplication)application).getDataSource();
        this.allCheeses=this.dataSource.getCheeseDao().allCheesesByName();
        this.cheeseCount=this.dataSource.getCheeseDao().countAllCheeses();
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


    public void previousPage() {
        allCheeses.previousPage();
    }

    public void nextPage() {
        allCheeses.nextPage();
    }

    public int getCurrentPageIndex() {
        return allCheeses.getPage();
    }
}
