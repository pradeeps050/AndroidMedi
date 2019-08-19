package com.orangeskill.elate.framework.network.networkstate;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

import org.greenrobot.eventbus.EventBus;


public class ConnectivityReceiver extends BroadcastReceiver {
    private Boolean previousNetstate = null;
    private Activity activity;
    private View view;
    SnackbarEventListener listener;
    boolean showSnack = true;


    public interface SnackbarEventListener {
        void onDismiss(Snackbar snackbar);

        void onShow(Snackbar snackbar);
    }

    public ConnectivityReceiver(View view) {
        this.view = view;
    }

    public ConnectivityReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = (cm == null )?null : cm.getActiveNetworkInfo();
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
            EventBus.getDefault().postSticky(new NetworkConnectivity(false));
        }else{
            EventBus.getDefault().postSticky(new NetworkConnectivity(true));
        }

        if (context instanceof SnackbarEventListener)
            listener = (SnackbarEventListener) context;
        show(com.orangeskill.elate.framework.application.ElateApplication.getInstance().isInternetConnected());
    }

    private void show(boolean isConnected) {
        previousNetstate = isConnected;
        if (!isConnected) {

            showSnack("No Internet Connection");
        } else {
            if (snackbar != null)
                snackbar.dismiss();
        }

    }

   /* public Boolean isInternetConnected() {
        return previousNetstate;
    }*/

    /*private static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }*/

    private Snackbar snackbar;

    private void showSnack(String msg) {
        if (view != null) {
            snackbar = Snackbar
                    .make(view, msg, Snackbar.LENGTH_LONG);

            snackbar.addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    if (listener != null)
                        listener.onDismiss(transientBottomBar);
                }

                @Override
                public void onShown(Snackbar sb) {
                    super.onShown(sb);
                    if (listener != null)
                        listener.onShow(sb);
                }
            });

            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        }
    }
    public void unRegisterCalled(){
        if (snackbar != null)
            snackbar.dismiss();
    }
}