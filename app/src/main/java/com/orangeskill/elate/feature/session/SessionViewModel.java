package com.orangeskill.elate.feature.session;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.orangeskill.elate.feature.session.data.SessionDataSource;
import com.orangeskill.elate.feature.session.model.TherapySession;

import java.util.List;

public class SessionViewModel extends ViewModel {
    private SessionDataSource dataSource;
    private MutableLiveData<TherapySession> sessionMutableLiveData = new MutableLiveData<>();

    public SessionViewModel() {
        dataSource = SessionDataSource.getInstance();
    }

    public LiveData<TherapySession> getSessionLiveData() {
        return sessionMutableLiveData;
    }

    public void getSession(int id) {
        dataSource.loadSession(id, sessionMutableLiveData);

    }
}
