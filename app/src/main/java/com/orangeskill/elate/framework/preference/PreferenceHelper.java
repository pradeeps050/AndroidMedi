package com.orangeskill.elate.framework.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.orangeskill.elate.framework.constantsValues.ConstantValues;

import java.util.Objects;
import java.util.Set;

import static com.orangeskill.elate.framework.preference.IPrefrenceHelperKeys.IS_FIRST_TIME_LAUNCH;


public class PreferenceHelper {
    @Nullable
    private static PreferenceHelper appPrefrence = null;
    @NonNull
    private final Context mContext;
    @Nullable
    private SharedPreferences preferences = null;

    private PreferenceHelper(@NonNull Context context) {
        preferences =
                context.getSharedPreferences(IPrefrenceHelperKeys.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        this.mContext = context;
    }

    public static PreferenceHelper getAppPrefs(@NonNull Context context) {
        synchronized (PreferenceHelper.class) {
            if (appPrefrence == null) appPrefrence = new PreferenceHelper(context);
        }

        return appPrefrence;
    }

    public void saveStringValue(@NonNull String key, String value) {
        SharedPreferences.Editor editor = Objects.requireNonNull(preferences).edit();
        editor.putString(key, value);
        editor.apply();

    }

    public void saveStringSet(@NonNull String key, Set<String> setValue){
        SharedPreferences.Editor editor = Objects.requireNonNull(preferences).edit();
        editor.putStringSet(key , setValue);
        editor.apply();
    }

    public Set<String> getStringSet(@NonNull String key){
        return Objects.requireNonNull(preferences).getStringSet(key , null);
    }

    public int getIntValue(String key) {
        return Objects.requireNonNull(preferences).getInt(key, -1);
    }

    public void saveIntValue(String key, int value) {
        SharedPreferences.Editor editor = Objects.requireNonNull(preferences).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void saveLongValue(String key, Long value) {
        SharedPreferences.Editor editor = Objects.requireNonNull(preferences).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLongValue(String key) {
        return Objects.requireNonNull(preferences).getLong(key, -1);
    }

    public void putDouble(final String key, final double value) {
        SharedPreferences.Editor editor = Objects.requireNonNull(preferences).edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.apply();
    }

    public double getDouble(final String key, final double defaultValue) {
        return Double.longBitsToDouble(Objects.requireNonNull(preferences).getLong(key, Double.doubleToLongBits(defaultValue)));
    }

    public String getStringValue(String key) {
        if (null == preferences)
            return "";

        return preferences.getString(key, "");
    }

    public void saveBooleanValue(String key, boolean value) {
        SharedPreferences.Editor editor = Objects.requireNonNull(preferences).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        return Objects.requireNonNull(preferences).getBoolean(key, false);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        SharedPreferences.Editor editor = Objects.requireNonNull(preferences).edit();
        editor.putBoolean(IPrefrenceHelperKeys.IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return Objects.requireNonNull(preferences).getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }


}
