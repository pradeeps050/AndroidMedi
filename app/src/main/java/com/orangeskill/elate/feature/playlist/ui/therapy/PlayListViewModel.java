package com.orangeskill.elate.feature.playlist.ui.therapy;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.orangeskill.elate.feature.playlist.ui.therapy.data.PlayListDataSource;
import com.orangeskill.elate.feature.playlist.ui.therapy.data.model.PlayList;

public class PlayListViewModel extends ViewModel {

    private MutableLiveData<PlayList> listMutableLiveData = new MutableLiveData<>();
    private PlayListDataSource dataSource;

    public PlayListViewModel() {
        dataSource = PlayListDataSource.getInstance();
    }

    public LiveData<PlayList> getPlayList() {
        return listMutableLiveData;
    }

    public void loadPlayList(int id) {
        dataSource.loadPlayList(id, listMutableLiveData);
    }
}
