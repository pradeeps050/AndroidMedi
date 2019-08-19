package com.orangeskill.elate.framework.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.orangeskill.elate.R;
import com.orangeskill.elate.framework.logger.Logger;
import com.orangeskill.elate.framework.application.ElateApplication;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;


public class UtilityMethods {

    private static final String TAG = UtilityMethods.class.getSimpleName();

    private static UtilityMethods mUtilityMethods;



    private UtilityMethods() {
    }

    public static UtilityMethods getInstance() {
        if (mUtilityMethods == null) {
            mUtilityMethods = new UtilityMethods();
        }
        return mUtilityMethods;
    }

    /**
     * @return Json String
     */
//    public static String createJsonFromModel(Object response) {
////        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
////        return gson.toJson(response);
//    }

    public static boolean isNetworkConnected(@Nullable Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnectedOrConnecting());
        }
        return false;
    }

    /**
     * Hide Keyboard on View Called From
     *
     * @param view View on which to hide Soft Keyboard
     */
    public static void hideKeyboard(@Nullable Context mContext, @Nullable View view) {
        if (mContext != null && view != null) {
            try {
                final InputMethodManager imm =
                        (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Exception e) {
                Logger.verbose(e.getMessage());
            }
        }
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            View view = activity.getCurrentFocus();
            final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && view != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

            }
        }
    }

    public static void launchEmailIntent(Context context, String[] toEmailIds, String subject, String body) {
        final Intent emailIntent = new Intent( android.content.Intent.ACTION_SEND);

        emailIntent.setType("text/html");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, toEmailIds);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,body);

        context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    public static void launchCallIntent(Context context, String phone) {
        if(context != null && !TextUtils.isEmpty(phone)) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone));
            context.startActivity(intent);
        }
    }




    private String replaceNulltoEmptyString(String str) {

        if (TextUtils.isEmpty(str)) {
            return "";
        } else {
            return str;
        }

    }

    public String ConvertTwoDecimalPlace(String str) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormat df = ((DecimalFormat)format);
        df.applyPattern("#,##0.00");
        boolean isNegative = false;
        Double number;
        try {
            number = Double.parseDouble(str);
            if (number < 0) {
                isNegative = true;
            }
        } catch (Exception e) {
            return "$" + str;
        }

        String convertedStr = df.format( Math.abs(number));
        if (isNegative) {
            convertedStr = "($" + convertedStr + ")";
            return convertedStr;
        }
        return "$" + convertedStr;

    }

    public static int dpToPx(final float dp) {
        return (int) (dp * com.orangeskill.elate.framework.application.ElateApplication.getInstance().getContext().getResources().getDisplayMetrics().density + 0.5f);
    }




    private boolean isCharOrDigit(char c){
        return Character.isLetter(c) || Character.isDigit(c);
    }









    /**
     * Open the link in web browser
     * @param context
     * @param url
     */
    public void openLink(Context context , String url){
        if(!TextUtils.isEmpty(url)) {
            Uri browserUri = Uri.parse(url);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, browserUri);
            context.startActivity(launchBrowser);
        }
    }



    public String getFormattedPhoneNumber(String phoneNumber){
        if(TextUtils.isEmpty(phoneNumber))
            return "";

        if (phoneNumber.length() == 10) {
            return phoneNumber.replaceAll("[^0-9]", "").replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
        } else if (phoneNumber.length() == 11) {
            return phoneNumber.replaceAll("[^0-9]", "").replaceFirst("(\\d{1})(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3-$4");
        } else {
            return phoneNumber;
        }
        /*String phone = phoneNumber;
        if(!TextUtils.isEmpty(phone)) {
            try {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    // Do something for lollipop and above versions
                    phone = PhoneNumberUtils.formatNumber(phoneNumber, "US");
                } else {
                    // do something for phones running an SDK before lollipop
                    phone = PhoneNumberUtils.formatNumber(phoneNumber);
                }
            } catch (Exception e) {
                Logger.e(TAG, "Error in formatting number " + e.getMessage());
            }
        }
        return phone;*/
    }



    public static boolean isListEmpty(List<?> datas) {
        return datas == null || datas.size() <= 0;
    }



    public static void showConfirmationToastMessage(String content){
        Toast.makeText(com.orangeskill.elate.framework.application.ElateApplication.getInstance().getContext(), content, Toast.LENGTH_LONG).show();
    }

    public static void showSnackBar(String msg, ViewDataBinding binding, Context context){
        if(binding == null || context == null)
            return;
        Snackbar snackbar = Snackbar
                .make(binding.getRoot(), msg, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(context.getResources().getColor(R.color.white));
        snackbar.show();
    }







    /** Returns the consumer friendly device name */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        }
        return manufacturer + " " + model;
    }

    /** Returns the consumer App Version name */
    public static String getVersionName(Activity activity) {
        PackageInfo pinfo = null;
        String versionName = null;
        try {
            pinfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            if(null!=pinfo)
            versionName = pinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return versionName;
    }

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                       Logger.d(TAG, "***** IP="+ ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Logger.d(TAG, ex.toString());
        }
        return null;
    }

}
