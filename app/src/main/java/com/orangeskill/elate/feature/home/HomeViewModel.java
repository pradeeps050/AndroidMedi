package com.orangeskill.elate.feature.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.orangeskill.elate.feature.home.data.HomeDataSource;
import com.orangeskill.elate.feature.home.model.Therapy;
import com.orangeskill.elate.feature.home.model.TherapyCategory;

import java.util.List;
import java.util.ListIterator;

public class HomeViewModel extends ViewModel {

    private HomeDataSource dataSource;
    private MutableLiveData<List<TherapyCategory>> listMutableLiveData = new MutableLiveData<>();

    public HomeViewModel(){
        dataSource = HomeDataSource.getInstance();
    }

    public LiveData<List<TherapyCategory>> getList() {
        return listMutableLiveData;
    }

    public void load() {
        dataSource.loadData(listMutableLiveData);
    }

}
